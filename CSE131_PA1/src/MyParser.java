
//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

import java_cup.runtime.*;

import java.util.Vector;



class MyParser extends parser
{

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	MyParser (Lexer lexer, ErrorPrinter errors)
	{
		m_lexer = lexer;
		m_symtab = new SymbolTable ();
		m_errors = errors;
		m_nNumErrors = 0;
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public boolean
	Ok ()
	{
		return (m_nNumErrors == 0);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public Symbol
	scan ()
	{
		Token		t = m_lexer.GetToken ();

		//	We'll save the last token read for error messages.
		//	Sometimes, the token is lost reading for the next
		//	token which can be null.
		m_strLastLexeme = t.GetLexeme ();

		switch (t.GetCode ())
		{
			case sym.T_ID:
			case sym.T_ID_U:
			case sym.T_STR_LITERAL:
			case sym.T_FLOAT_LITERAL:
			case sym.T_INT_LITERAL:
			case sym.T_CHAR_LITERAL:
				return (new Symbol (t.GetCode (), t.GetLexeme ()));
			default:
				return (new Symbol (t.GetCode ()));
		}
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public void
	syntax_error (Symbol s)
	{
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public void
	report_fatal_error (Symbol s)
	{
		m_nNumErrors++;
		if (m_bSyntaxError)
		{
			m_nNumErrors++;

			//	It is possible that the error was detected
			//	at the end of a line - in which case, s will
			//	be null.  Instead, we saved the last token
			//	read in to give a more meaningful error 
			//	message.
			m_errors.print (Formatter.toString (ErrorMsg.syntax_error, m_strLastLexeme));
		}
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public void
	unrecovered_syntax_error (Symbol s)
	{
		report_fatal_error (s);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public void
	DisableSyntaxError ()
	{
		m_bSyntaxError = false;
	}

	public void
	EnableSyntaxError ()
	{
		m_bSyntaxError = true;
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public String 
	GetFile ()
	{
		return (m_lexer.getEPFilename ());
	}

	public int
	GetLineNum ()
	{
		return (m_lexer.getLineNumber ());
	}

	public void
	SaveLineNum ()
	{
		m_nSavedLineNum = m_lexer.getLineNumber ();
	}

	public int
	GetSavedLineNum ()
	{
		return (m_nSavedLineNum);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoProgramStart()
	{
		// Opens the global scope.
		m_symtab.openScope ();
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoProgramEnd()
	{
		m_symtab.closeScope ();
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	//TODO: has erros on declare global var. if global and local are both declared, it reports
	//redeclared. Which is wrong! 
	void
	DoVarDecl (Vector<STO> lstIDs, Type t)
	{
		for (int i = 0; i < lstIDs.size (); i++)
		{
			STO sto = lstIDs.elementAt(i);
			String id = sto.getName();
			Type type = sto.getType();
		
			if (m_symtab.accessLocal (id) != null)
			{
				m_nNumErrors++;
				m_errors.print (Formatter.toString(ErrorMsg.redeclared_id, id));
			}
			if(type != null)
			{
				if(type.isAssignable(t))
				{
					VarSTO 	var = new VarSTO (id);
					var.setType(t);
					m_symtab.insert (var);
				}
				else
				{
					m_nNumErrors++;
	                m_errors.print (Formatter.toString(ErrorMsg.error8_Assign,
	                 type.getName(), t.getName()));
				}
			}
			else
			{
				VarSTO 	var = new VarSTO (id);
				var.setType(t);
				m_symtab.insert (var);
			}
		}
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoExternDecl (Vector<String> lstIDs)
	{
		for (int i = 0; i < lstIDs.size (); i++)
		{
			String id = lstIDs.elementAt (i);

			if (m_symtab.accessLocal (id) != null)
			{
				m_nNumErrors++;
				m_errors.print (Formatter.toString(ErrorMsg.redeclared_id, id));
			}

			VarSTO 		sto = new VarSTO (id);
			m_symtab.insert (sto);
		}
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoConstDecl (Vector<STO> lstIDs, Type t)
	{
		for (int i = 0; i < lstIDs.size (); i++)
		{
			STO sto = lstIDs.elementAt (i);
			if(sto.isError()) return;
			String id = sto.getName();

			if (m_symtab.accessLocal (id) != null)
			{
				m_nNumErrors++;
				m_errors.print (Formatter.toString (ErrorMsg.redeclared_id, id));
			}
			else if ((sto.getType() != null) && !(sto.getType().isAssignable(t))) {
                m_nNumErrors++;
                m_errors.print (Formatter.toString(ErrorMsg.error8_Assign,
                		sto.getType().getName(), t.getName()));
            }
		
			ConstSTO 	c_sto = new ConstSTO (id, t, ((ConstSTO) sto).getValue());
			m_symtab.insert (c_sto);
		}
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoTypedefDecl (Vector<String> lstIDs, Type baseType)
	{
		for (int i = 0; i < lstIDs.size (); i++)
		{
			String id = lstIDs.elementAt (i);

			if (m_symtab.accessLocal (id) != null)
			{
				m_nNumErrors++;
				m_errors.print (Formatter.toString(ErrorMsg.redeclared_id, id));
			}
			
			Type newType = baseType.copy(); 
			newType.setAlias(id);
			
			TypedefSTO 	sto = new TypedefSTO (id, newType);
			m_symtab.insert (sto);
		}
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoStructdefDecl (String id)
	{
		if (m_symtab.accessLocal (id) != null)
		{
			m_nNumErrors++;
			m_errors.print (Formatter.toString(ErrorMsg.redeclared_id, id));
		}
		
		TypedefSTO 	sto = new TypedefSTO (id);
		m_symtab.insert (sto);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoFuncDecl_1 (Type returnType,String id)
	{
		if (m_symtab.accessLocal (id) != null)
		{
			STO sto = m_symtab.accessLocal(id);
			if(!sto.isFunc())
			{
				m_nNumErrors++;
				m_errors.print (Formatter.toString(ErrorMsg.redeclared_id, id));
			}
			else
			{
				FunctionPointerType type = new FunctionPointerType (id, 4);
                type.setReturnType (returnType);
                //type.setByRef(type);
                ((FuncSTO) sto).setReturnType (returnType);
                ((FuncSTO) sto).setFuncType (type);
                m_symtab.openScope ();	//open new scope
                m_symtab.setFunc ((FuncSTO)sto);	//current function we're in is set
                return;
			}
		}
	
		m_funcName = id;
		FuncSTO sto = new FuncSTO (id);
		
		/*if(byRef) sto.setRef(byRef); */
		sto.setReturnType(returnType);
		
		m_symtab.insert (sto);	//insert into current scope
		m_symtab.openScope ();	//open new scope
		m_symtab.setFunc (sto);	//current function we're in is set
	}


	//----------------------------------------------------------------
	// check 6c
	//----------------------------------------------------------------
	void
	DoFuncDecl_2 ()
	{
		FuncSTO func = m_symtab.getFunc();
		/*if (!(func.getReturnType() instanceof VoidType) && !m_symtab.curScope().getRet() && !ext) 
		{
            m_nNumErrors++;
            m_errors.print (ErrorMsg.error6c_Return_missing);
        }*/
		m_symtab.closeScope ();	//close scope, pops top scope off
		m_symtab.setFunc (null);	// we are back in outer scope
	}


	//----------------------------------------------------------------
	// check5. check function parameters
	//----------------------------------------------------------------
	void
	DoFormalParams (Vector<VarSTO> params)
	{
		if (m_symtab.getFunc () == null)
		{
			m_nNumErrors++;
			m_errors.print ("internal: DoFormalParams says no proc!");
		}

		// insert parameters here
		if(params != null)
		{
			//FuncSTO sto = m_symtab.getFunc ();
			for(int i = 0; i < params.size(); i++)
			{
				VarSTO var = params.get(i);
				m_symtab.insert(var);
				
			}
		}
		
	}
	
	// ----------------------------------------------------------------
	//
	// ----------------------------------------------------------------
	STO DoFuncCall(STO sto, Vector args) 
	{
		if(sto instanceof ErrorSTO) return sto;
		
		STO stoDes;
        Type returnT;
        Vector<VarSTO> params;
        boolean error = false;
        boolean byRef = false;
		
		if (!sto.isFunc() && !sto.getType().isFuncPtr() ) 
		{
			m_nNumErrors++;
			m_errors.print(Formatter.toString(ErrorMsg.not_function,
					sto.getName()));
			return (new ErrorSTO(sto.getName()));
		}
		
		params = ((FunctionPointerType) sto.getType()).getParams();
        returnT = ((FunctionPointerType) sto.getType()).getReturnType();
        byRef = ((FunctionPointerType) sto.getType()).getByRef();
		
		//do check5 no.1
		if (params.size() != args.size()) 
		{
            m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error5n_Call,
                                                  args.size(), params.size()));
            return (new ErrorSTO (sto.getName()));
        }
		
		for(int i = 0; i < params.size(); i++)
		{
			STO arg = (STO) args.get(i);
            if (arg.isError()) return arg;
            VarSTO parameter = params.get(i);
            Type aType = arg.getType();
            Type bType = parameter.getType();
            
            /* Do check5 no.2
             * A parameter is declared as pass-by-value (default) and the corresponding argument's
             * type is not assignable to the parameter type
             */
            if ( !(parameter.isRef()) && !(aType.isAssignable(bType)) ) 
            {
                m_nNumErrors++;
                m_errors.print (Formatter.toString(ErrorMsg.error5a_Call,
                		aType.getName(), parameter.getName(), bType.getName()));
                error = true;
            }
            /* Do check5 no.3, no.4
             * no.3: A parameter is declared as pass-by-reference (using the &)
             * 		 and the corresponding argument's type is not equivalent to
             * 		 the parameter type;
             * no.4: A parameter is declared as pass-by-reference and the 
             * 		 corresponding argument is not a modifiable L-value.
             */
            else if (parameter.isRef()) 
            {
            	// no.3
                if ( !(aType.isEquivalent(bType)) ) {
                    m_nNumErrors++;
                    m_errors.print (Formatter.toString(ErrorMsg.error5r_Call,
                           aType.getName(), parameter.getName(), bType.getName()));
                    error = true;
                } 
                // no.4
                else if ( !arg.isModLValue() && !aType.isArray() ) {
                    m_nNumErrors++;
                    m_errors.print (Formatter.toString(ErrorMsg.error5c_Call,
                               parameter.getName(), aType.getName()));
                    error = true;
                }
            }            
		}
		if (error) return new ErrorSTO (sto.getName());

		return (sto);
	}
	
	/*
	 * Do check 6
	 */
	void DoReturnStmtCheck(STO returnExpr)
	{
		FuncSTO func = m_symtab.getFunc();
		Type ret = func.getReturnType();
		
		
		if(returnExpr instanceof ErrorSTO) return;
		else if(ret instanceof VoidType && returnExpr == null)
		{
			//Do nothing
		}
		// check 6a
		// Detect an illegal return statement where no Expr is specified
		// and the return type is not void
		else if (!(ret instanceof VoidType) && (returnExpr == null)) {
            m_nNumErrors++;
            m_errors.print (ErrorMsg.error6a_Return_expr);
            return;
        } 
		// check 6b no.1
		// For functions declared to return by value, an error should be 
		// generated if the type of return expression is not assignable to
		// the return type of the function
		else if(!func.getRef())
		{
			if(!returnExpr.getType().isAssignable(ret))
			{
				m_nNumErrors++;
	            m_errors.print (Formatter.toString(ErrorMsg.error6a_Return_type,
	             (returnExpr.getType()).getName(), ret.getName()));
	            return;
			}
		}
		// check 6b no.2
		// For functions declared to return by reference, an error should
		// be generated if
		// - the type of the return expression is not equivalent to the 
		// return type of the function;
		// - the return expression is not a modifiable L-value.
		else if(func.getRef())
		{
			if(!returnExpr.getType().isEquivalent(ret))
			{
				m_nNumErrors++;
				m_errors.print (Formatter.toString(ErrorMsg.error6b_Return_equiv,
			             (returnExpr.getType()).getName(), ret.getName()));
	            return;
			}
			else if(!returnExpr.isModLValue())
			{
				m_nNumErrors++;
				m_errors.print (ErrorMsg.error6b_Return_modlval);
	            return;
			}
		}
		
	}
	
	/*
	 * check 7. exit check
	 */
	STO DoExitStmtCheck(STO expr)
	{
		Type ExprType = expr.getType();
        if(!(ExprType instanceof IntType)) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error7_Exit,
            		ExprType.getName()));
            return new ErrorSTO ("NaN for Exit");
        }
        return expr;
	}

	/*
	 * check8a. Initialization check and variable assign
	 */
	STO DoInitCheck(String id, STO sto)
	{
		if(sto == null) return new VarSTO ("empty", null);
		if(sto != null && sto.isError()) return sto;	
		
		// not known at compile time
		if((m_static || m_symtab.getLevel() == 1) )
		{
			if( sto == null || (!sto.isConst() && !sto.isFunc()) )
			{
				m_nNumErrors++;
				m_errors.print(Formatter.toString(ErrorMsg.error8a_CompileTime, 
            		id));
				return new ErrorSTO("Expr is not known at compile time");
			}
		}
		return sto;
	}
	
	/*
	 * check8b. Initialization check for const
	 */
	STO DoConstInit(String id, STO sto)
	{
		if(sto != null && sto.isError()) return sto;
		
		// not known at compile time
		if( sto == null || !sto.isConst() )
		{
			m_nNumErrors++;
			m_errors.print(Formatter.toString(ErrorMsg.error8b_CompileTime, 
		        		id));
			return new ErrorSTO("Expr is not known at compile time");
		}
		else
		{
			sto = new ConstSTO (id, sto.getType(),
		             ((ConstSTO) sto).getValue());
		}
		return sto;
	}
	
	/*
	 * set static, for check8
	 */
	void setStatic()
	{
		m_static = true;
	}
	
	/*
	 * reset static, for check8
	 */
	void resetStatic()
	{
		m_static = false;
	}
	
	/*
	 * check10. do array declarition check
	 */
		//----------------------------------------------------------------
		// check10. 
		// Given a type declaration such as -- Type[Index] List1;
		// An error should be generated if
		// �� the type of the index expression is not equal to int
		// �� the value of the index expression is not known at compile time
		//		(i.e., not a constant);
		// �� the value of the index expression is not greater than 0.
		//----------------------------------------------------------------
	STO DoArrayCheck(STO sto)
	{
		if(sto.isError()) return sto;
		// Good place to do the array checks
		if (sto instanceof ErrorSTO)
			return new ErrorSTO("illegal array decl");
		// value of expr is not known at compile time
		if (!sto.isConst()) 
		{
			m_nNumErrors++;
            m_errors.print (ErrorMsg.error10c_Array);
            return new ErrorSTO(sto.getName());
		} 
		else if (!sto.getType().isIntType()) 
		{
			m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error10i_Array,
             sto.getType().getName()));
            return new ErrorSTO(sto.getName());
		} 
		else if(  ((ConstSTO)sto).getIntValue() <= 0 )
		{
			m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error10z_Array,
             ((ConstSTO) sto).getIntValue()));
            return new ErrorSTO(sto.getName());
		}
		else return new ExprSTO (sto.getName(), new ArrayType("array",
	             ((ConstSTO) sto).getIntValue()));
	}

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoBlockOpen ()
	{
		// Open a scope.
		m_symtab.openScope ();
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoBlockClose()
	{
		m_symtab.closeScope ();
	}
	
	STO DoIfWhileCheck(STO a, boolean isIf)
	{
		if(a instanceof ErrorSTO) return a;
		Type aType = a.getType();
		
		//if ExprSTO is not bool or int, return error
		if( ! (aType instanceof BoolType || aType instanceof IntType) )
		{
			m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error4_Test,
             a.getName()));
		}
		return a;
	}

	//----------------------------------------------------------------
	// check 3
	//----------------------------------------------------------------
	STO
	DoAssignExpr (STO stoDes, STO expr)
	{
		//syntax error
		if(stoDes.isError()) return stoDes;
	    if(expr.isError())	return expr;
	    //3a 
	    
		if (!stoDes.isModLValue())
		{
			// Good place to do the assign checks
			m_nNumErrors++;
			m_errors.print(ErrorMsg.error3a_Assign);
			return new ErrorSTO(stoDes.getName());
		}
		
		
		//3b
		if (!expr.getType().isAssignable(stoDes.getType()))
		{
			m_nNumErrors++;
			m_errors.print(Formatter.toString(ErrorMsg.error3b_Assign, 
				expr.getType().getName(), stoDes.getType().getName()));
			return new ErrorSTO(stoDes.getName());
		}
		
		//create returnval
		ExprSTO returnValue = new ExprSTO(stoDes.getName(), stoDes.getType());
		//return R-value
		//returnValue.setIsRValue(true);
		
		//return returnValue;
		return stoDes;
	}


	STO
	DoBinaryExpr(STO a, Operator o, STO b) 
	{
		STO result = o.checkOperands(a, b);
		if (result instanceof ErrorSTO) {
			// do stuff
			m_nNumErrors++;
			//m_errors.print (Formatter.toString(ErrorMsg.not_function, sto.getName()));
			m_errors.print (result.getName());
		}
		return result ;
	}
	
	STO DoUnaryOp(Operator o, STO a)
	{
		if(a instanceof ErrorSTO) return a;
		
		STO result = o.checkOperands(a, null);
		
		if (result instanceof ErrorSTO) {
			m_nNumErrors++;
			//m_errors.print (Formatter.toString(ErrorMsg.not_function, sto.getName()));
			m_errors.print (result.getName());
		}
		return result ;	
	}
	
	STO DoUnarySign(String sign, STO sto)
	{
		STO stoDes;
        if (sto instanceof ConstSTO) {
            if (sign == "-") {
                stoDes = new ConstSTO (sto.getName(), sto.getType(),
                 0 - ((ConstSTO) sto).getValue());
            } else {
                stoDes = new ConstSTO (sto.getName(), sto.getType(),
                 ((ConstSTO) sto).getValue());
            }
        } else {
            stoDes = new ExprSTO(sign + sto.getName(), sto.getType());
        }
		return stoDes;
	}
	
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	STO
	DoDesignator2_Dot (STO sto, String strID)
	{
		// Good place to do the struct checks

		return sto;
	}


	
	STO
	DoDesignator2_Array (STO sto, STO expr)
	{
			
		return sto;
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	STO
	DoDesignator3_ID (String strID)
	{
		STO		sto;

		//System.out.println("strID: " + strID + "  access: " + m_symtab.access (strID));
		//System.out.println("strID: " + strID + "  accessGlobal: " + m_symtab.accessGlobal (strID));
		
		if ((sto = m_symtab.access (strID)) == null)
		{
			
			m_nNumErrors++;
		 	m_errors.print (Formatter.toString(ErrorMsg.undeclared_id, strID));	
			sto = new ErrorSTO (strID);
		}			
		
		return (sto);
	}
	
	STO
	DoDesignator3a_ID (String strID)
	{
		STO		sto;

		//System.out.println("strID: " + strID + "  access: " + m_symtab.access (strID));
		//System.out.println("strID: " + strID + "  accessGlobal: " + m_symtab.accessGlobal (strID));
		
		if((sto = m_symtab.accessGlobal(strID)) == null )
		{
			m_nNumErrors++;
		 	m_errors.print (Formatter.toString(ErrorMsg.error0g_Scope, strID));	
			sto = new ErrorSTO (strID);		
		}
				
		
		return (sto);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	STO
	DoQualIdent (String strID)
	{
		STO		sto;

		if ((sto = m_symtab.access (strID)) == null)
		{
			m_nNumErrors++;
		 	m_errors.print (Formatter.toString(ErrorMsg.undeclared_id, strID));	
			return (new ErrorSTO (strID));
		}

		if (!sto.isTypedef())
		{
			m_nNumErrors++;
			m_errors.print (Formatter.toString(ErrorMsg.not_type, sto.getName ()));
			return (new ErrorSTO (sto.getName ()));
		}

		return (sto);
	}


//----------------------------------------------------------------
//	Instance variables
//----------------------------------------------------------------
	private Lexer			m_lexer;
	private ErrorPrinter		m_errors;
	private int 			m_nNumErrors;
	private String			m_strLastLexeme;
	private boolean			m_bSyntaxError = true;
	private int			m_nSavedLineNum;
	private String		m_funcName;
	private boolean 	m_static;

	private SymbolTable		m_symtab;
}
