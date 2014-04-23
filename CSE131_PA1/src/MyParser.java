
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
	DoVarDecl (Vector<VariableBox<STO, STO>> lstIDs, Type t)
	{
		if (t == null) return;
		
		for (int i = 0; i < lstIDs.size (); i++)
		{
			VariableBox<STO, STO> box = lstIDs.elementAt(i);
			//get first STO. stored variable and its name
			STO variable = box.getVariable();
			String id = variable.getName();
			Type varType = variable.getType();
			//get second STO. stored expr and its type
			STO expr = box.getExpr();
			Type exprType = null;
			
			if(variable.isError()) return;
			if(expr != null)
				exprType = expr.getType();
		
			VarSTO var;
			
			if (m_symtab.accessLocal (id) != null)
			{
				m_nNumErrors++;
				m_errors.print(Formatter.toString(ErrorMsg.redeclared_id,id));
			}			
			else if(exprType == null && varType == null)
			{		
				if(t instanceof ArrayType)
					var = new VarSTO(id, t, true, false);
				else
					var = new VarSTO (id, t);
				m_symtab.insert (var);
				
			}
			else if(varType instanceof PointerType)
			{
				((PointerType) varType).setType(t);
				if(exprType != null && exprType.isAssignable(varType))
				{
					if(t instanceof ArrayType)
						var = new VarSTO(id, varType, true, false);
					else
						var = new VarSTO (id, varType);
					m_symtab.insert (var);
				}
				else if(exprType == null)
				{
                    var = new VarSTO(id, varType, true, true);
                    m_symtab.insert (var);
				}
				else	//right is not assignable to left
				{
					m_nNumErrors++;
	                m_errors.print(Formatter.toString(ErrorMsg.error8_Assign,
	                		exprType.getName(), ((PointerType) varType).getName()));
				}
			}
			else if(varType instanceof ArrayType)
			{
				Type arrType = new ArrayType("Array", 1, 
						((ArrayType) varType).getArraySize(), t);
				var = new VarSTO(id, arrType, true, false);
				m_symtab.insert (var);
			}
			else if(varType == null)
			{
				if(exprType.isAssignable(t))
				{
					if(t instanceof ArrayType)
						var = new VarSTO(id, t, true, false);
					else
						var = new VarSTO (id, t);
					m_symtab.insert (var);
				}
				//not assignable
				else
				{
					m_nNumErrors++;
	                m_errors.print(Formatter.toString(ErrorMsg.error8_Assign,
	                		exprType.getName(), t.getName()));
				}
			}
			else	//varType != null, exprType != null
			{
				if(t instanceof ArrayType)
					var = new VarSTO(id, t, true, false);
				else
					var = new VarSTO (id, t);
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
		if(baseType == null) return;
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
			
			TypedefSTO sto = new TypedefSTO(id, newType);
			m_symtab.insert(sto);
		}
	}
	
	/*
     * check13a. initialize local struct name for declarition and 
     * usage
     */
	void setStruct(String name)
	{
		m_structType = new StructType(name, -1, new Vector<STO>());
        m_structName = name;
	}
	
	/*
     * check13a. reset local struct name for declarition and 
     * usage
     */
	void resetStruct()
	{
		m_structName = null;
	}

	//----------------------------------------------------------------
	//	insert struct into symbol table
	//----------------------------------------------------------------
	void
	DoStructdefDecl (String id, Vector<STO> fieldList)
	{
		if (m_symtab.accessLocal (id) != null)
		{
			m_nNumErrors++;
			m_errors.print (Formatter.toString(ErrorMsg.redeclared_id, id));
		}
		
		m_structType.setSize(m_structSize);
        m_structType.setFieldList(fieldList);
        m_structSize = 0;
		TypedefSTO 	sto = new TypedefSTO (id, m_structType);
		m_symtab.insert (sto);
	}

	/*
	 * check13. 
	 * - a. the same identifier twice in the same struct declaration.
	 * - b. invalid recursive struct definition
	 */
	Vector<STO> DoFieldListCheck(Type t, Vector<STO> idList)
	{
		Vector<STO> stoList = new Vector<STO>();
		VarSTO var;
		for (int i = 0; i < idList.size (); i++)
        {
			STO sto = idList.get(i);
			String id = sto.getName();
			// check13a
			if(m_symtab.accessLocal(id) != null)
			{
				m_nNumErrors++;
                m_errors.print (Formatter.toString(ErrorMsg.error13a_Struct,
                		id));
			}
			// check13b
			else if( m_structName.equals(t.getName()) && !t.isPointer() )
			{
				m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error13b_Struct,
                 sto.getName()));	
			}
			else 
			{
                if (t.isArray()) 
                	var = new VarSTO (id, t, true, false);
                else 
                	var = new VarSTO(id, t);
                m_symtab.insert (var);
                m_structSize += t.getSize();
                stoList.addElement(var);
            }
        }
		return stoList;
	}

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoFuncDecl_1 (Type returnType, String id, boolean ref)
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
                type.setByRef(ref);
                ((FuncSTO) sto).setReturnType (returnType);
                ((FuncSTO) sto).setFuncType (type);
                m_symtab.openScope ();	//open new scope
                m_symtab.setFunc ((FuncSTO)sto);	//current function we're in is set
                return;
			}
		}
	
		m_funcName = id;
		FuncSTO sto = new FuncSTO (id);
		
		if(ref) sto.setRef(ref); 
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
		m_symtab.getFunc().setParams(params);
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
        Type returnType;
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
		
		//if(sto.isFunc() || sto.isVar())
		//{
			if(sto.isVar())
			{
				params = ((FunctionPointerType) sto.getType()).getParams();
				returnType = ((FunctionPointerType) sto.getType())
						.getReturnType();
				byRef = ((FunctionPointerType) sto.getType()).getByRef();
			}
			else
			{
				params = ((FuncSTO) m_symtab.access(sto.getName())).getParams();
				returnType = ((FuncSTO) m_symtab.access(sto.getName()))
						.getReturnType();
				byRef = ((FuncSTO)m_symtab.access(sto.getName())).getRef();
			}
		
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
             * A parameter is declared as pass-by-value (default) and the 
             * corresponding argument's
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
		
		STO ret;
		if (byRef) { 
            ret = new VarSTO ("result", returnType);
            ((VarSTO) ret).setRef();
        } else ret = new ExprSTO ("result", returnType);
		return ret;
		//}
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
            m_errors.print(ErrorMsg.error6a_Return_expr);
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
	            m_errors.print(Formatter.toString(ErrorMsg.error6a_Return_type,
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
			m_errors.print(Formatter.toString(ErrorMsg.error6b_Return_equiv,
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
	 * check8a. Static and global Initialization check and variable assign
	 */
	STO DoInitCheck(String id, STO sto)
	{
		if(sto == null) return new VarSTO ("empty", null);
		if(sto instanceof ErrorSTO) return sto;	
		
		// not known at compile time
		if((m_static || m_symtab.getLevel() == 1) )
		{
			if( ( !(sto.isConst()) && !(sto.isFunc()) &&
					!(sto.getType().isArray()) ) )
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
		if(sto instanceof ErrorSTO) return sto;
		
		// not known at compile time
		if( !(sto instanceof ConstSTO) )
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
		// ¡ñ the type of the index expression is not equal to int
		// ¡ñ the value of the index expression is not known at compile time
		//		(i.e., not a constant);
		// ¡ñ the value of the index expression is not greater than 0.
		//----------------------------------------------------------------
	STO DoArrayCheck(STO sto)
	{
		STO ret;
		if(sto.isError()) return sto;
		// Good place to do the array checks
		if (sto instanceof ErrorSTO)
			return new ErrorSTO("illegal array decl");
		// value of expr is not known at compile time
		if (!(sto.getType() instanceof IntType)) 
		{
			m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error10i_Array,
             sto.getType().getName()));
            return new ErrorSTO(sto.getName());
		} 
		else if (!(sto instanceof ConstSTO)) 
		{
			m_nNumErrors++;
            m_errors.print (ErrorMsg.error10c_Array);
            return new ErrorSTO(sto.getName());
		} 
		else if(  ((ConstSTO)sto).getIntValue() <= 0 )
		{
			m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error10z_Array,
             ((ConstSTO) sto).getIntValue()));
            return new ErrorSTO(sto.getName());
		}
		else 
			ret = new ExprSTO (sto.getName(), 
				new ArrayType( "array", sto.getType().getSize(), ((ConstSTO) sto).getIntValue() )   );
		return ret;
	}
	
	/*
	 * check12a. Continue statement check
	 * Errors should be generated if either statement is not within the 
	 * body of a while loop.
	 */
	void DoContinueCheck() {
        if(m_while == 0) {
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error12_Continue);
        }
    }
	
	/*
	 * check12b. Break statement check
	 * Errors should be generated if either statement is not within the 
	 * body of a while loop.
	 */
	void DoBreakCheck() {
        if(m_while == 0) {
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error12_Break);
        }
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
	
	// for check12
	void InWhile()
	{
        m_while = 1;
    }

	// for check12
    void OutWhile()
    {
        m_while = 0;
    }
    
    /*
     * check15a-1
     * An error should be generated if
     * ¡ñ The type of ptr is not a pointer type for the * operator
     */
    STO DoDereferenceCheck(STO sto)
    {
    	if(sto.isError()) return sto;
    
    	STO retSTO;
    	
    	if(!sto.getType().isPointer())
    	{
    		m_nNumErrors++;
    		m_errors.print (Formatter.toString(ErrorMsg.error15_Receiver,
            sto.getType().getName()));
    		return new ErrorSTO ("Not a Pointer type");
    	}
    	//TODO: what if sto is a struct type?? 
    	//else return baseType of sto
    	else
    	{
    		retSTO = new VarSTO(sto.getName(), ((PointerType)sto.getType()).getBaseType() );
    	}
	
    	return retSTO;
    }
    
    /*
     * check15a-2
     * An error should be generated if
     *	¡ñ 1. The type of ptr is not a pointer to a struct for the -> operator.
     * 	¡ñ 2. The right side is a field within the struct. use errmsg from check14.
     */
    STO DoArrowCheck(STO sto, String id)
    {
    	if(sto.isError())	return sto;
    	//check 15a.1
    	if(!sto.getType().isPointer() || 
    		!((PointerType)sto.getType()).getBaseType().isStruct() )
    	{
    		m_nNumErrors++;
    		m_errors.print(Formatter.toString(ErrorMsg.error15_ReceiverArrow,
    				sto.getType().getName()));
    		return new ErrorSTO("ptr is not a ptr to a struct for -> op");
    	}
    	//check 15a.2
    	else
    	{
    		boolean found = false;
			STO strSTO = m_symtab.access(((PointerType) sto.getType())
					.getBaseType().getName());
			Vector<STO> fieldList = ((StructType)strSTO.getType()).getFieldList();
			STO elt = null;
			for(int i = 0; i < fieldList.size(); i++)
			{
				elt = fieldList.get(i);
				if(elt.getName().equals(id))
				{
					found = true;
					break;
				}
			}
			//if not found, return errormsg14
			if(found == false)
			{
				m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error14f_StructExp,
                		id, strSTO.getType().getName()));
                return new ErrorSTO("right side is not a field in struct");
			}
			return elt;
    	}
    }

    /*
     * check16a. check new statement --- new x;
     *  an error should be generated if
     *	¡ñ x is not a modifiable L-value;
     *	¡ñ x is not of a valid pointer type.
     */
    void DoNewStmtCheck(STO sto)
    {
    	if(sto.isError()) return;
    	if(!sto.isModLValue()) 
    	{
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error16_New_var);
        }
    	else if (!(sto.getType().isPointer()))
    	{
            m_nNumErrors++;
            m_errors.print(Formatter.toString (ErrorMsg.error16_New,
             sto.getType().getName()));
        }
    }
    
    /*
     * check16b. check delete statement --- delete x;
     *  an error should be generated if
     *	¡ñ x is not a modifiable L-value;
     *	¡ñ x is not of a valid pointer type.
     */
    void DoDeleteStmtCheck(STO sto)
    {
    	if(sto.isError()) return;
    	if(!sto.isModLValue()) 
    	{
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error16_Delete_var);
        }
    	else if (!(sto.getType().isPointer()))
    	{
            m_nNumErrors++;
            m_errors.print(Formatter.toString (ErrorMsg.error16_Delete,
             sto.getType().getName()));
        }
    }
    
    /*
     * check19. 
     * An error should be generated 
     * - a. if the operand is not a type
     * - b. if the operand is not addressable.
     */
    STO DoSizeof(Object obj)
    {
    	STO sto;
    	//a.
    	if(obj instanceof Type)
    	{
    		sto =  new ConstSTO("result", new IntType("int", 4),
    	             (double)((Type) obj).getSize());
    	}
    	//b.
    	else if( !((STO)obj).getIsAddressable() )
    	{
    		sto =  new ConstSTO("result", new IntType("int", 4),
    	             (double)((STO) obj).getType().getSize());
    	}
    	else
    	{
    		m_nNumErrors++;
            m_errors.print(ErrorMsg.error19_Sizeof);
            sto =  new ErrorSTO ("Not a type or is not Addressable");
    	}
    	
    	return sto;
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
			return new ErrorSTO("illegal assignment (check3)");
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
	//	check14. check struct usage
	//	Given a designator such as
	//			MyStruct.SomeField
	//	an error should be generated if
	// ¡ñ a. the type of MyStruct is not a struct type;
	// ¡ñ b. the type of MyStruct has no field named SomeField
	//----------------------------------------------------------------
	STO
	DoDesignator2_Dot (STO sto, String strID)
	{
		if(sto.isError()) return new ErrorSTO("error");
		// Good place to do the struct checks
		Type type = sto.getType();
		// check14a
		if(!(type instanceof StructType)) 
		{
			m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error14t_StructExp, 
            		type.getName()));
            return new ErrorSTO("Illegal Struct Usage, not a struct");
		}
		// check14b
		boolean found = false;
        STO field = new ErrorSTO("Field not found");
        Vector<STO> fieldList = ((StructType)type).getFieldList();
        for(int i = 0; i < fieldList.size(); i++) 
        {
            field = fieldList.get(i);
            //if there is a field 
            if(strID.equals(field.getName())) 
            {
                found = true;
                break;
            }
        }
        if(found == false) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error14f_StructExp,
            		strID, type.getName()));
            return new ErrorSTO("Illegal Struct Usage - no such field");
        }
		return field;
	}


	/*
	 * check11. Error if
	 * ¡ñ a. The type of the desig preceding any [] operator is not an array
	 *   or pointer type;
	 * ¡ñ b. The type of the index expression (nIndex in this case) is not 
	 * 	 equivalent to int;
	 * ¡ñ c. If the index expression is a constant, an error should be generate
	 *   if the index is outside the bounds of the array 
	 *   (does not apply when the designator preceding the [] is of pointer
	 *    type).
	 */
	STO
	DoDesignator2_Array (STO sto, STO expr)
	{
		if(sto.isError()) return new ErrorSTO("error");
		if(expr.isError()) return new ErrorSTO("error");
		
		STO retSTO;
		Type aType = sto.getType();
		Type bType = expr.getType();
		
		//check a
		if(!(aType instanceof ArrayType) && !(aType instanceof PointerType))
		{
			m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error11t_ArrExp,
             aType.getName()));
            return new ErrorSTO ("Check11 error. Illegal Array Usage");
		}
		else if(aType instanceof PointerType)
		{
			Type t = ((PointerType) aType).getBaseType();
			retSTO = new VarSTO(aType.getName(), t);
		}
		//check b
		else if(!(bType instanceof IntType))
		{
			m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error11i_ArrExp, 
            		bType.getName()));
            return new ErrorSTO ("Check11 error. Inequivalent to int");
		}
		//check c
		else if(expr instanceof ConstSTO)
		{
			int index = ((ConstSTO) expr).getIntValue();
			int upperBound = ((ArrayType)aType).getArraySize();
			//check array index bounds. need >=0 and < size
			if(index < 0 || index >= upperBound)
			{
				m_nNumErrors++;
                m_errors.print (Formatter.toString(ErrorMsg.error11b_ArrExp,
                		index, upperBound));
                return new ErrorSTO ("Array index out of bound");
			}
			//declare a new array
			else
			{
				Type t = ((ArrayType) aType).getBaseType();
                if (t.isArray()) 
                	retSTO = new VarSTO (sto.getName(), t, true, false);
                else 
                	retSTO = new VarSTO (sto.getName(), t);
                //retSTO.setIsArrayE();
                VariableBox<STO, STO> array = new VariableBox<STO, STO>
                (sto, expr);
                retSTO.setArray (array);
			}
		}
		else
		{
			Type t = ((ArrayType) aType).getBaseType();
            if (t.isArray()) 
            	retSTO = new VarSTO (sto.getName(), t, true, false);
            else 
            	retSTO = new VarSTO (sto.getName(), t);
            //retSTO.setIsArrayE();
            VariableBox<STO, STO> array = new VariableBox<STO, STO>
            (sto, expr);
            retSTO.setArray (array);		
		}
			
		return retSTO;
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
			System.out.println("In DoDesignator3_ID. ");
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
		
		if(strID.equals(m_structName)) 
		{
            return new VarSTO(strID, m_structType);
        }
		
		if ((sto = m_symtab.access (strID)) == null && !strID.equals(m_structName))
		{
			m_nNumErrors++;
			System.out.println("in doqualIdent");
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
	private int			m_while;
	private String      m_structName = null;
    private int         m_structSize = 0;
    private StructType  m_structType;

	private SymbolTable		m_symtab;
}
