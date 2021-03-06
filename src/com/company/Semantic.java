package com.company;


public class Semantic {

    public void Semantic(){


    }


    public boolean validate(String tipo, String d){

        switch (tipo){
            case "Morty":

                try{

                    int a = Integer.parseInt(d);

                    return true;

                }catch (Exception e){
                    System.out.println(e);
                    return false;
                }

            case "Summer":


                if(d.matches("\"(.*?)\""))
                    return true;

            case "Meeseek":


                if(d.matches("Alive|Dead"))
                    return true;




        }

        return false;

    }

    public void analize(){


        for( int i = 0; i < Trick.trick.size(); i++ ){

            if( Trick.trick.get(i).tipo.equals("decl")){


                String tipo = Trick.trick.get(i).token.get(0).lexema;
                String id = Trick.trick.get(i).token.get(1).lexema;

                if(Variables.searchById(id)){
                    Errors.add("Variable " + id + " already declared");
                }else{

                    Variable v = new Variable();
                    v.id = id;
                    v.tipo = tipo;

                    //Si tambien hay asignacion
                    //Validar tipo de dato
                    if( Trick.trick.get(i).token.size() > 3 ){

                        String dato = Trick.trick.get(i).token.get(3).lexema;

                        if( this.validate( tipo, dato ) ){

                            Variables.add(v);

                        }else{

                            Errors.add("Type error: Variable expecting its type");

                        }

                    }else{

                    Variables.add(v);

                    }

                }


            }else if(Trick.trick.get(i).tipo.equals("assign")){

                //Si es una asignacion busca que exista, si no existe marca error si si exista valida tipo

                String id = Trick.trick.get(i).token.get(0).lexema;
                String dato = Trick.trick.get(i).token.get(2).lexema;

                if(Variables.searchById(id)){

                    String tipo = Variables.TypeById(id);

                    if(this.validate( tipo, dato )){

                        //Remplazar valor

                    }else{


                        Errors.add("Type error: Variable expecting its type");

                    }


                }else{


                    Errors.add("Variable " + id + " not declared");
                }




            }


        }


    }
}
