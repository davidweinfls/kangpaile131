
//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

import java_cup.runtime.*;

import java.util.Vector;



class MyParser extends parser
{
	private AssemblyCodeGenerator myAsWriter;
	private boolean debug = false;

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
		
		myAsWriter = new AssemblyCodeGenerator("C:\\Users\\David Wei\\git\\Project1\\CSE131_PA1\\src\\rc.s");
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
		
		if(myAsWriter.has_rodata)
			myAsWriter.writeBuffer(myAsWriter.rodata_buffer);
		if(myAsWriter.has_data)
			myAsWriter.writeBuffer(myAsWriter.data_buffer);
		if(myAsWriter.has_bss)
			myAsWriter.writeBuffer(myAsWriter.bss_buffer);
		if(myAsWriter.has_text)
			myAsWriter.writeBuffer(myAsWriter.text_buffer);	
		
		myAsWriter.dispose();
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	void
	DoVarDecl (Vector<VariableBox<STO, STO>> lstIDs, Type t)
	{
		if (t == null) return;
		
		myAsWriter.resetReg();
		
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
			//var without init
			else if(exprType == null && varType == null)
			{		
				if(t instanceof ArrayType)
					var = new VarSTO(id, t, true, false);
				else
					var = new VarSTO (id, t);
				
				//P2: 
				if(m_symtab.getLevel() == 1)
				{
					var.setBase("%%g0");
					var.setGlobalOffset(id);
					if(!m_static)
					{
						var.setGlobal();
					}
					if(t.isStructType())
						myAsWriter.writeGlobalStruct(id, false, expr, t, m_static);
					else
						myAsWriter.writeGlobalVariable(id, false, expr, t, m_static);
				}
				else
				{
					if(m_static)
					{
						var.setGlobalOffset("static_" + m_funcName + "_" + id + (num_of_staticVar++) );
                        var.setBase("%%g0");
                        String static_name = var.getGlobalOffset();
                        myAsWriter.writeStaticVariable(static_name, false, expr, t);
                    }
					else
					{
                        m_currOffset -= t.getSize();
                        var.setOffset(m_currOffset);
                        var.setBase("%%fp");
                        myAsWriter.writeLocalVariableWOInit(var);
                    }
				}
				
				m_symtab.insert (var);
				
			}
			else if(varType instanceof PointerType)
			{
				((PointerType) varType).setType(t);
				//with init
				if(exprType != null && exprType.isAssignable(varType))
				{
					//old project1 version
					/*if(t instanceof ArrayType)
						var = new VarSTO(id, varType, true, false);
					else
						var = new VarSTO (id, varType);*/
					var = new VarSTO(id, varType, true, true);
					
					//gloabl
					if(m_symtab.getLevel() ==1)
					{
						if(!m_static)
							var.setGlobal();
						var.setBase("%%g0");
						var.setGlobalOffset(id);
						myAsWriter.writeGlobalVariable(id, true, expr, varType, m_static);
					}
					//local
					else
					{
						if(m_static)
						{
							var.setGlobalOffset("static_" + m_funcName + "_" + id + (num_of_staticVar++) );
	                        var.setBase("%%g0");
	                        String static_name = var.getGlobalOffset();
	                        myAsWriter.writeStaticVariable(static_name, true, expr, varType);
						}
						else
						{
							if(exprType.isIntType() && t.isFloatType())
							{
								myAsWriter.intToFloat(expr);
							}
							else 
								myAsWriter.getValue(expr);
							
							m_currOffset -= t.getSize();
                            var.setOffset(m_currOffset);
                            var.setBase("%%fp");
                            myAsWriter.writeLocalVariableWInit(var, expr);
						}
					}
					m_symtab.insert (var);
				}
				//ptr w/o init
				else if(exprType == null)
				{
                    var = new VarSTO(id, varType, true, true);
                  //gloabl
					if(m_symtab.getLevel() ==1)
					{
						if(!m_static)
							var.setGlobal();
						var.setBase("%%g0");
						var.setGlobalOffset(id);
						myAsWriter.writeGlobalVariable(id, false, expr, t, m_static);
					}
					//local
					else
					{
						if(m_static)
						{
							var.setGlobalOffset("static_" + m_funcName + "_" + id + (num_of_staticVar++) );
	                        var.setBase("%%g0");
	                        String static_name = var.getGlobalOffset();
	                        myAsWriter.writeStaticVariable(static_name, false, expr, varType);
						}
						else
						{
							m_currOffset -= t.getSize();
                            var.setOffset(m_currOffset);
                            var.setBase("%%fp");
                            myAsWriter.writeLocalVariableWOInit(var);
						}
					}
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
				
				//if global array
				if(m_symtab.getLevel() == 1)
				{
					var.setBase("%%g0");
					var.setGlobalOffset(id);
					var.setGlobal();
					//write to assembly
					if(m_static)
						myAsWriter.writeGlobalArray(var, true);
					else
						myAsWriter.writeGlobalArray(var, false);
				}
				//local array
				else
				{
					//check if static or not
					if(m_static)
					{
						var.setGlobalOffset("static_" + m_funcName + "_" + id + (num_of_staticVar++) );
                        var.setBase("%%g0");
                        //write to assembly
                        myAsWriter.writeStaticArray(var);
					}
					else
					{
						m_currOffset -= arrType.getSize();
                        var.setOffset(m_currOffset);
                        var.setBase("%%fp");
                        myAsWriter.writeLocalVariableWOInit(var);
					}
				}
				m_symtab.insert (var);
			}
			//var with init
			else if(varType == null)
			{
				if(exprType.isAssignable(t))
				{
					if(t instanceof ArrayType)
						var = new VarSTO(id, t, true, false);
					else
						var = new VarSTO (id, t);
					
					//P2: 
					//global
					if(m_symtab.getLevel() == 1)
					{
						var.setBase("%%g0");
						var.setGlobalOffset(id);
						if(!m_static)
						{
							var.setGlobal();
						}
						myAsWriter.writeGlobalVariable(id, true, expr, t, m_static);
					}
					//local
					else
					{
						if(m_static)
						{
							var.setGlobalOffset("static_" + m_funcName + "_" + id + (num_of_staticVar++) );
	                        var.setBase("%%g0");
	                        String static_name = var.getGlobalOffset();
	                        myAsWriter.writeStaticVariable(static_name, true, expr, t);
						}
						else
						{
							if(t.isFloatType() && exprType.isIntType())
							{
								myAsWriter.intToFloat(expr);
							}
							else
								myAsWriter.getValue(expr);
							m_currOffset -= t.getSize();
                            var.setOffset(m_currOffset);
                            var.setBase("%%fp");
                            myAsWriter.writeLocalVariableWInit(var, expr);
						}
						
					}
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

			VarSTO sto = new VarSTO (id);
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
			// global const
			if(m_symtab.getLevel() == 1)
			{
				c_sto.setBase("%%g0");
				c_sto.setGlobalOffset(id);
				if(!m_static)
                    c_sto.setGlobal();
				//write assembly
				myAsWriter.writeConstVar(id, m_static, true, c_sto, t);
			}
			else
			{
				c_sto.setBase("%%g0");
				c_sto.setGlobalOffset("const_" + m_funcName + "_" + id + (num_of_constVar++));
				//write assembly
				myAsWriter.writeConstVar(c_sto.getGlobalOffset(), m_static, false, c_sto, t);
			}
			
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
			else if( m_structName.equals(t.getName()) && !t.isPointerType() )
			{
				m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error13b_Struct,
                 sto.getName()));	
			}
			else 
			{
                if (t.isArrayType()) 
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
			//overloaded
			else
			{
				FunctionPointerType type = new FunctionPointerType (id, 4);
                type.setReturnType (returnType);
                type.setByRef(ref);
                
                //TODO: make sure to handle overload func's return type 
                //set sto's return type, both don't change its funcptr's return type
                ((FuncSTO) sto).setReturnType (returnType);
                
                //add this new funcptr type to overload func list
                ((FuncSTO) sto).addOverloadFunction(type);
                
                m_symtab.openScope ();	//open new scope
                m_symtab.setFunc ((FuncSTO)sto);	//current function we're in is set
                return;
			}
		}
	
		
		FuncSTO sto = new FuncSTO (id);
		
		if(ref) sto.setRef(ref); 
		
		//set returntype for both FuncSTO and FunctionPointerType
		sto.setReturnType(returnType);
		((FunctionPointerType)sto.getType()).setReturnType (returnType);
		
		m_funcName = id;
		m_symtab.insert (sto);	//insert into current scope
		m_symtab.openScope ();	//open new scope
		m_symtab.setFunc (sto);	//current function we're in is set
		
		myAsWriter.writeFuncDec(id);
	}


	//----------------------------------------------------------------
	// check 6c
	//----------------------------------------------------------------
	void
	DoFuncDecl_2 (boolean extern)
	{
		FuncSTO func = m_symtab.getFunc();
		if (!(func.getReturnType() instanceof VoidType) && 
				!m_symtab.getCurrScope().getReturned() && !extern) 
		{
            m_nNumErrors++;
            m_errors.print (ErrorMsg.error6c_Return_missing);
        }
		
		myAsWriter.writeFuncClose(func.getName(), m_currOffset, func.getReturnType());
		
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
		FuncSTO sto = m_symtab.getFunc ();
		
		// insert parameters here
		if(params != null)
		{
			int paramOffset = 68;
			//check if function is overloaded
			if(!(m_symtab.getFunc().isOverloaded()))
			{
                m_symtab.getFunc().setParams(params);
			}
			//need to reset its params
            else
            {             
            	//call funcSTO.setOverloadedParams to handle overload func
                boolean check = sto.setOverloadedParams (params);
                if(!check)
                {
                    m_nNumErrors++;
                    m_errors.print (Formatter.toString(ErrorMsg.error22_Decl,
                    m_symtab.getFunc().getName()));
                    return;	//if illegal overload function declaration, return
                    		//dont insert param to symtab
                }
            }
			//FuncSTO sto = m_symtab.getFunc();
			for(int i = 0; i < params.size(); i++)
			{
				VarSTO var = params.get(i);
				
				if(var.getType() == null) return;
				
				else if(var.getType().isArrayType())
				{
					var.setRef();
                }
				
				//for P2:
				var.setBase("%%fp");
				var.setOffset(paramOffset);
				
				//update offset
				if(!(var.isRef()))
				{
					paramOffset += var.getType().getSize();
				}
				else
				{
					paramOffset += 4;
				}
				
				//write to assembly
				myAsWriter.writeParameter(var, i);
				
				m_symtab.insert(var);
				
			}
		}
	}
	
	// ----------------------------------------------------------------
	//	handle function call. check 6. also did with overload function call
	// ----------------------------------------------------------------
	STO DoFuncCall(STO sto, Vector args) 
	{
		if(sto instanceof ErrorSTO) return sto;
		
        Type returnType;
        Vector<VarSTO> params;
        boolean error = false;
        boolean byRef = false;
		
		if (!sto.isFunc() && !sto.getType().isFunctionPointerType()) {
			m_nNumErrors++;
			m_errors.print(Formatter.toString(ErrorMsg.not_function,
					sto.getName()));
			return (new ErrorSTO(sto.getName()));
		}

		if ((sto.isFunc() && !(((FuncSTO)sto).isOverloaded()) ) || sto.isVar()) {
			if (sto.isVar()) {
				params = ((FunctionPointerType) sto.getType()).getParams();
				returnType = ((FunctionPointerType) sto.getType())
						.getReturnType();
				byRef = ((FunctionPointerType) sto.getType()).getByRef();
			} else {
				params = ((FuncSTO) m_symtab.access(sto.getName())).getParams();
				returnType = ((FuncSTO) m_symtab.access(sto.getName()))
						.getReturnType();
				byRef = ((FuncSTO) m_symtab.access(sto.getName())).getRef();
			}

			// do check5 no.1
			if (params.size() != args.size()) {
				m_nNumErrors++;
				m_errors.print(Formatter.toString(ErrorMsg.error5n_Call,
						args.size(), params.size()));
				return (new ErrorSTO(sto.getName()));
			}
			
			//for project2: 
			if(sto.isVar())
			{
				myAsWriter.writeFuncPtr(sto);
			}

			for (int i = 0; i < params.size(); i++)
			{
				STO arg = (STO) args.get(i);
				if (arg instanceof ErrorSTO)
					return arg;
				VarSTO parameter = params.get(i);
				Type aType = arg.getType();
				Type bType = parameter.getType();

				/*
				 * Do check5 no.2 A parameter is declared as pass-by-value
				 * (default) and the corresponding argument's type is not
				 * assignable to the parameter type
				 */
				if (!(parameter.isRef()) && !(aType.isAssignable(bType)))
				{
					m_nNumErrors++;
					m_errors.print(Formatter.toString(ErrorMsg.error5a_Call,
							aType.getName(), parameter.getName(),
							bType.getName()));
					error = true;
				}
				/*
				 * Do check5 no.3, no.4 no.3: A parameter is declared as
				 * pass-by-reference (using the &) and the corresponding
				 * argument's type is not equivalent to the parameter type;
				 * no.4: A parameter is declared as pass-by-reference and the
				 * corresponding argument is not a modifiable L-value.
				 */
				else if (parameter.isRef())
				{
					// no.3
					if (!(aType.isEquivalent(bType)))
					{
						m_nNumErrors++;
						m_errors.print(Formatter.toString(
								ErrorMsg.error5r_Call, aType.getName(),
								parameter.getName(), bType.getName()));
						error = true;
					}
					// no.4
					else if (!arg.isModLValue() && !aType.isArrayType())
					{
						m_nNumErrors++;
						m_errors.print(Formatter.toString(
								ErrorMsg.error5c_Call, parameter.getName(),
								aType.getName()));
						error = true;
					}
				}
				
				//pass parameters in %i0~i5
				if(i < 6 && !error)
				{
					myAsWriter.writePassParameter(arg, parameter, parameter.isRef(), i);
				}
				else
				{
					
				}
			}
			if (error)
				return new ErrorSTO(sto.getName());
			
			STO ret;
			if (byRef) {
				ret = new VarSTO("result", returnType);
				((VarSTO) ret).setRef();
				//TODO
				m_currOffset -= returnType.getSize();
				ret.setBase("%%fp");
				ret.setOffset(m_currOffset);
			}
			else
			{
				ret = new ExprSTO("result", returnType);
				m_currOffset -= returnType.getSize();
				ret.setBase("%%fp");
				ret.setOffset(m_currOffset);
			}
			
			
			//write to assembly
			myAsWriter.writeFuncCall(sto, ret, byRef);
			return ret;
		}
		// function is overloaded
		else 
		{
			// check illegal overloaded function call
			FunctionPointerType check = ((FuncSTO) sto).checkOverload(args);
			if (check == null)
			{
				m_nNumErrors++;
				m_errors.print (Formatter.toString(ErrorMsg.error22_Illegal,
						sto.getName()));
				return (new ErrorSTO (sto.getName()));
            }
			returnType = check.getReturnType(); 
			byRef = check.getByRef();
			STO ret;
            if (byRef)
            { 
                ret = new VarSTO ("result", returnType);
                ((VarSTO) ret).setRef();
            }
            else 
            	ret = new ExprSTO ("result", returnType);
            return ret;
		}
	}
	
	/*
	 * Do check 6
	 */
	void DoReturnStmtCheck(STO returnExpr)
	{
		FuncSTO func = m_symtab.getFunc();
		Type ret = func.getReturnType();
		m_symtab.getCurrScope().markReturned();
		
		if(returnExpr instanceof ErrorSTO) {return;}
		else if(ret instanceof VoidType && returnExpr == null)
		{
			//Do nothing
			myAsWriter.writeReturnStmt(returnExpr, ret, func.getRef());
			return;
		}
		//if function return type is void and return expression is not null
		else if(ret instanceof VoidType && returnExpr != null)
		{
			if(returnExpr.getType() != null)
			{
				m_nNumErrors++;
            	m_errors.print(Formatter.toString(ErrorMsg.error6a_Return_type,
            			(returnExpr.getType()).getName(), ret.getName()));
			}
            return;
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
			//TODO: need to test this
			if(returnExpr.getType() == null) return;
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
		myAsWriter.writeReturnStmt(returnExpr, ret, func.getRef());
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
        myAsWriter.writeExitStmt(expr);
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
			if( ( !(sto.isConst())  && !(sto.isFunc())
					/* !(sto.getType().isArrayType())*/ ) )
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
	
	//P2: store local const literals value at run time
	void DoConstValue(STO sto)
	{
		if(m_symtab.getFunc() != null && m_symtab.getLevel() != 1)
		{
			m_currOffset -= sto.getType().getSize();
            sto.setOffset(m_currOffset);
            sto.setBase("%%fp");
            myAsWriter.writeConstValue (sto);        
		}
			
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
		//  the type of the index expression is not equal to int
		//  the value of the index expression is not known at compile time
		//		(i.e., not a constant);
		//  the value of the index expression is not greater than 0.
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
        myAsWriter.writeContinue();
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
        myAsWriter.writeBreak();
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
             aType.getName()));
		}
		if(isIf)
			myAsWriter.writeIf(a);
		else
			myAsWriter.writeWhile(a);
		
		return a;
	}
	
	void DoIfBlock()
	{
		myAsWriter.writeElse();
	}
	
	void DoEndIf(boolean ifwhile)
	{
		myAsWriter.writeCloseBlock(ifwhile);
	}
	
	// for check12
	void InWhile()
	{
        m_while++;
        myAsWriter.writeWhileStart();
    }

	// for check12
    void OutWhile()
    {
        m_while--;
        myAsWriter.writeWhileEnd();
    }
    
    /*
     * check15a-1
     * An error should be generated if
     *  The type of ptr is not a pointer type for the * operator
     */
    STO DoDereferenceCheck(STO sto)
    {
    	if(sto.isError()) return sto;
    
    	STO retSTO;
    	
    	if(!sto.getType().isPointerType())
    	{
    		m_nNumErrors++;
    		m_errors.print (Formatter.toString(ErrorMsg.error15_Receiver,
            sto.getType().getName()));
    		return new ErrorSTO ("Not a Pointer type");
    	}
    	//TODO: what if sto is a struct type?? 
    	else if(((PointerType) sto.getType()).getBaseType().isStructType())
    	{
    		STO s = m_symtab.access (((PointerType) sto.getType()).
    				getBaseType().getName());
    		Type type = s.getType();
    		retSTO = new VarSTO (sto.getName(), type);
        }
    	//else return baseType of sto
    	else
    	{
    		retSTO = new VarSTO(sto.getName(), ((PointerType)sto.getType()).getBaseType() );
    		m_currOffset -= ((PointerType)sto.getType()).getBaseType().getSize();
    	}
	
    	retSTO.setIsDeref();
    	retSTO.setPointer(sto);
    	
    	return retSTO;
    }
    
    /*
     * check15a-2
     * An error should be generated if
     *	 1. The type of ptr is not a pointer to a struct for the -> operator.
     * 	 2. The right side is a field within the struct. use errmsg from check14.
     */
    STO DoArrowCheck(STO sto, String id)
    {
    	if(sto.isError())	return sto;
    	
    	STO elt = null;
    	int offset = 0;
    	//check 15a.1
    	if(!sto.getType().isPointerType() || 
    		!((PointerType)sto.getType()).getBaseType().isStructType() )
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
			
			for(int i = 0; i < fieldList.size(); i++)
			{
				elt = fieldList.get(i);
				if(elt.getName().equals(id))
				{
					found = true;
					break;
				}
				offset += elt.getType().getSize();
			}
			//if not found, return errormsg14
			if(found == false)
			{
				m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error14f_StructExp,
                		id, strSTO.getType().getName()));
                return new ErrorSTO("right side is not a field in struct");
			}
			
    	}
    	STO retSTO = elt.copy();
        retSTO.setBase(sto.getBase());
        retSTO.setFieldOffset(offset);
        retSTO.setIsStructField();
        retSTO.setStruct(sto.copy());
        
        return retSTO;
    }

    /*
     * check16a. check new statement --- new x;
     *  an error should be generated if
     *	 x is not a modifiable L-value;
     *	 x is not of a valid pointer type.
     */
    void DoNewStmtCheck(STO sto)
    {
    	if(sto.isError()) return;
    	if(!sto.isModLValue()) 
    	{
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error16_New_var);
        }
    	else if (!(sto.getType().isPointerType()))
    	{
            m_nNumErrors++;
            m_errors.print(Formatter.toString (ErrorMsg.error16_New,
             sto.getType().getName()));
        }
    	
    	//for project2:
    	myAsWriter.writeNewStmt(sto);
    }
    
    /*
     * check16b. check delete statement --- delete x;
     *  an error should be generated if
     *	 x is not a modifiable L-value;
     *	 x is not of a valid pointer type.
     */
    void DoDeleteStmtCheck(STO sto)
    {
    	if(sto.isError()) return;
    	if(!sto.isModLValue()) 
    	{
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error16_Delete_var);
        }
    	else if (!(sto.getType().isPointerType()))
    	{
            m_nNumErrors++;
            m_errors.print(Formatter.toString (ErrorMsg.error16_Delete,
             sto.getType().getName()));
        }
    	//for project2:
    	myAsWriter.writeDeleteStmt(sto);
    }
    
    /*
     * check18
     */
    Type DoFuncptr(Type returnType, boolean byRef, Vector<VarSTO> param)
    {
    	Type retType = new FunctionPointerType("funcptr", returnType, byRef, param);
    	return retType;
    }
    
    /*
     * check19. 
     * An error should be generated 
     * - a. if the operand is not a type
     * - b. if the operand is not addressable.
     */
    STO DoSizeof(Object obj)
    {
    	if(obj instanceof ErrorSTO) return new ErrorSTO("wrong param in  sizeof()");
    	
    	STO sto;
    	//a.
    	if(obj instanceof Type)
    	{
    		sto =  new ConstSTO("result", new IntType("int", 4),
    	             (double)((Type) obj).getSize());
    		sto.setIsAddressable(false);
    	}
    	//b.
    	else if( ((STO)obj).getIsAddressable() )
    	{
    		Type t = ((STO) obj).getType();
    		sto =  new ConstSTO("result", new IntType("int", 4),
    	             (double)t.getSize());
    		sto.setIsAddressable(false);
    	}
    	else
    	{
    		m_nNumErrors++;
            m_errors.print(ErrorMsg.error19_Sizeof);
            sto =  new ErrorSTO ("Not a type or is not Addressable");
    	}
    	
    	m_currOffset -= 4;
    	sto.setBase("%%fp");
    	sto.setOffset(m_currOffset);
    	
    	myAsWriter.writeSizeof(sto, ((ConstSTO)sto).getIntValue() );
    	
    	return sto;
    }
    
    /*
     * check20 do typecast
     */
    STO DoTypeCast(Type t, STO sto)
    {
    	if(sto.isError()) return sto;
        Type type = sto.getType ();
        STO retSTO;
        //check invalid typecast
        if ((!(type.isBasicType()) && !(type.isPointerType()) && !(type.isNullPointerType())) ||
            (!(t.isBasicType()) && !(t.isPointerType())))
        {
            m_nNumErrors++;
            m_errors.print (Formatter.toString (ErrorMsg.error20_Cast,
             type.getName(), t.getName()));
            retSTO = new ErrorSTO ("illegal Type cast");
        }
        else if(sto instanceof ConstSTO)
        {
			Double boolVal = 0.0;
			Double v = ((ConstSTO) sto).getValue();
			if (t.isBoolType() && v != 0)
				boolVal = 1.0;
			else if (t.isBoolType() && v == null)
				boolVal = 0.0;
			if(t.isBoolType())
			{
				retSTO = new ConstSTO(sto.getName(), t, boolVal);
				retSTO.setIsAddressable(false);
			}
			else
			{
				retSTO = new ConstSTO(sto.getName(), t, v);
				retSTO.setIsAddressable(false);
			}
		}
        else
        {
			retSTO = new ExprSTO(sto.getName(), t);
		}
        
        m_currOffset -= t.getSize();
        retSTO.setOffset(m_currOffset);
        retSTO.setBase("%%fp");
        
        myAsWriter.writeTypeCast(retSTO, sto);
        retSTO.setIsTypeCast();
    	return retSTO;
    }
    
    /*
     * check21. addressof operator
     */
    STO DoAddressOf(STO sto)
    {
    	STO retSTO = null;
    	if(sto instanceof ErrorSTO) return sto;
    	
    	//if not addressable, return error
		if(!sto.getIsAddressable()) 
		{
			m_nNumErrors++;
			m_errors.print(Formatter.toString(ErrorMsg.error21_AddressOf, sto
					.getType().getName()));
			retSTO = new ErrorSTO("Designator not addressable");
		}
		else
		{
			PointerType ptrType = new PointerType(sto.getType().getName() + "*", 4, sto.getType());
			retSTO = new ExprSTO(sto.getName(), ptrType);
			//for project2:
			m_currOffset -= 4;
			retSTO.setOffset(m_currOffset);
			retSTO.setBase("%%fp");
            myAsWriter.writeAddressOf(sto, retSTO);
		}
    	
    	return retSTO;
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

		myAsWriter.writeAssignExpr(stoDes, expr);
		
		return stoDes;
	}


	STO
	DoBinaryExpr(STO a, Operator o, STO b) 
	{
		STO result = o.checkOperands(a, b);
		
		//if(debug) myAsWriter.writeDebug(a.getName() + o.getName() + b.getName());
		
		if (result instanceof ErrorSTO) {
			// do stuff
			m_nNumErrors++;
			//m_errors.print (Formatter.toString(ErrorMsg.not_function, sto.getName()));
			m_errors.print (result.getName());
		}
		
		myAsWriter.resetReg();
		
		m_currOffset -= result.getType().getSize();
        result.setOffset(m_currOffset);
        result.setBase("%%fp");

        if (m_symtab.getLevel() != 1)
            myAsWriter.writeBinaryExpr(a, o, b, result);
        
		return result ;
	}
	
	void DoIncDecOp(STO sto, Operator o, boolean pre)
	{
		
	}
	
	//handle ++, --, !
	STO DoUnaryOp(Operator o, STO a, boolean incdec, boolean pre)
	{
		if(a instanceof ErrorSTO) return a;
		
		STO result = o.checkOperands(a, null);
		
		if (result instanceof ErrorSTO) {
			m_nNumErrors++;
			//m_errors.print (Formatter.toString(ErrorMsg.not_function, sto.getName()));
			m_errors.print (result.getName());
		}
		
		m_currOffset -= result.getType().getSize();
		result.setOffset(m_currOffset);
        //result.setOffset(a.getOffset());
        result.setBase("%%fp");
        
        if (m_symtab.getLevel() != 1)
            myAsWriter.writeUnaryExpr(a, o.getName(), result);
       
        if(incdec)
        {
        	if(pre)
        	{
        		return myAsWriter.writePre(a, o, result);
        	}
			else
			{
				//post inc/dec, need to return value before inc to doAssignExpr(var, expr)
				m_currOffset -= result.getType().getSize();
		        result.setOffset(m_currOffset);
		        result.setBase("%%fp");
				return myAsWriter.writePost(a, o, result);
				//return result;
			}
        }
        
		return result ;	
	}
	
	// handle +a, -a
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
        m_currOffset -= stoDes.getType().getSize();
        stoDes.setOffset(m_currOffset);
        stoDes.setBase("%%fp");
        myAsWriter.resetReg();
        
        //if (!sto.isConst()) 
        	//myAsWriter.getValue(sto);
        myAsWriter.writeUnaryExpr(sto, sign, stoDes);
		return stoDes;
	}
	
	//----------------------------------------------------------------
	//	check14. check struct usage
	//	Given a designator such as
	//			MyStruct.SomeField
	//	an error should be generated if
	//  a. the type of MyStruct is not a struct type;
	//  b. the type of MyStruct has no field named SomeField
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
        int offset = 0;
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
            offset += field.getType().getSize();
        }
        if(found == false) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error14f_StructExp,
            		strID, type.getName()));
            return new ErrorSTO("Illegal Struct Usage - no such field");
        }
        
        //instead of return the field, need to create a new sto to return
        //update its base, offset, struct type
        STO retSTO = field.copy();
        retSTO.setBase(sto.getBase());
        retSTO.setFieldOffset(offset);
        retSTO.setIsStructField();
        retSTO.setStruct(sto.copy());
        
		return retSTO;
	}


	/*
	 * check11. Error if
	 *  a. The type of the desig preceding any [] operator is not an array
	 *   or pointer type;
	 *  b. The type of the index expression (nIndex in this case) is not 
	 * 	 equivalent to int;
	 *  c. If the index expression is a constant, an error should be generate
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
		//TODO: if left is a pointer Type
		else if(aType instanceof PointerType)
		{
			Type t = ((PointerType) aType).getBaseType();
			retSTO = new VarSTO(aType.getName(), t);
			//TODO:
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
                if (t.isArrayType()) 
                	retSTO = new VarSTO (sto.getName(), t, true, false);
                else 
                	retSTO = new VarSTO (sto.getName(), t);
                
                //if int x[3], sto is x, expr is 3
                VariableBox<STO, STO> array = new VariableBox<STO, STO>
                (sto, expr);
                retSTO.setArray (array);
                //for project2 easiness, turn array flag on
                retSTO.setIsArray();
			}
		}
		else
		{
			Type t = ((ArrayType) aType).getBaseType();
            if (t.isArrayType()) 
            	retSTO = new VarSTO (sto.getName(), t, true, false);
            else 
            	retSTO = new VarSTO (sto.getName(), t);
            
            VariableBox<STO, STO> array = new VariableBox<STO, STO>
            (sto, expr);
            retSTO.setArray (array);	
            //for project2 easiness, turn array flag on
            retSTO.setIsArray();
		}
		
		if(!(aType.isPointerType()))
		{
			
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
	
	//P2: 
	// do print statement for cout
	void DoPrint(STO sto)
	{
		myAsWriter.writePrint(sto);
	}
	
	//P2
	void DoCin(STO sto)
	{
        if (sto.isModLValue())
            myAsWriter.writeCin(sto);
    }
	
	//P2
	void DoShortCircuit(STO sto, String op)
	{
		if(op.equals("&&"))
		{
			myAsWriter.writeAnd(sto);
		}
		else
		{
			myAsWriter.writeOr(sto);
		}
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
    
    private int m_currOffset = 0;
    private int num_of_staticVar = 0;
    private int num_of_constVar = 0;

	private SymbolTable		m_symtab;
}
