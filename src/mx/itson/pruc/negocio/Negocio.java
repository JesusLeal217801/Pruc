/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pruc.negocio;

/**
 * Contiene matodos utilizados para realizar diferentes operaciones.
 * @author Jesús Eduardo Leal Duarte - 217801
 */
public class Negocio {
    
    /**
     * Obtiene diferentes datos del usuario para obtener ciertos digitos.
     * @param nombre Nombre del usuario.
     * @param paterno Primer apellido del usuario.
     * @param materno Segundo apellido del usuario.
     * @param año Año de nacimiento del usuario.
     * @param mes Mes de nacimiento del usuario.
     * @param dia Dia de nacimiento del usuario.
     * @param genero Genero del usuario.
     * @param entidad Entidad federativa o Estado de origen del usuario.
     * @return Un dato llamado CURP.
     */
    public String generarCurp (String nombre, String paterno, String materno,
            int año, int mes, int dia, String genero, String entidad){
        
        char[] caracteres;
        String curp = "";
        String nombreCompleto;
        boolean error = false;
        String segundoNombre = "";
        String mesCurp = "";
        String añoTexto = Integer.toString(año);
        String diaTexto = Integer.toString(dia);
        String generoTexto = "";
        String entidadTexto = "";
        String digitoFinal;
        String mensaje = "";
        int diaMes = 0;
        
        //----------------------------------------------------------------------------------
        
        try{
        /*Hacemos todo el texto a mayuscula y le quitamos los
          caracteres que nos darian problemas.*/      
            
        nombreCompleto = nombre + paterno + materno;
        
        if(nombre.length() == 0 || paterno.length() == 0){
            error = true;
            mensaje = String.valueOf(error);
            return mensaje;
            
        }
            
        nombre = nombre.toUpperCase().replace("Á", "A")
            .replace("É", "E").replace("Í", "I")
             .replace("Ó", "O").replace("Ú", "U").trim();
        
        paterno = paterno.toUpperCase().replace("Á", "A")
            .replace("É", "E").replace("Í", "I")
            .replace("Ó", "O").replace("Ú", "U").trim();
        
        materno = materno.toUpperCase().replace("Á", "A")
            .replace("É", "E").replace("Í", "I")
            .replace("Ó", "O").replace("Ú", "U").trim();
            
        nombreCompleto = nombre + paterno + materno;
            
        for (int x = 0; x < nombreCompleto.length(); x++) {
            char c = nombreCompleto.charAt(x);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                error = true;
                break;
            }else{ 
                error = false;
            }
        }
            
        if(error == true){
                
            mensaje = String.valueOf(error);
            return mensaje;
        }
        
        // ---------------------------------------------------------------------------------

        /*Primera letra y la primera vocal interna del primer apellido.*/
        caracteres = paterno.toCharArray();
        for(int x=0; x<caracteres.length; x++){
            
            if (x == 0){
                curp = curp + caracteres[x];
            }
            
            if(caracteres[x] == 'A' || caracteres[x] == 'E' || 
               caracteres[x] == 'I' || caracteres[x] == 'O' || caracteres[x] == 'U'){
                
                curp = curp + caracteres[x];
                break;
            } 
        }
        
        //----------------------------------------------------------------------------------
        
        /*Primera letra del segundo apellido. 
          En caso de no tener segundo apellido, se colocará X.*/
        if(materno.length() == 0){
            String sinApellido = "X";
            curp = curp + sinApellido;
        }else{
            caracteres = materno.toCharArray();
            curp = curp + caracteres[0];  
        }
        
        //----------------------------------------------------------------------------------
        
        /*Primera letra del nombre de pila. 
          En nombres compuestos que comiencen con José o María, 
          se tomará en cuenta la primera letra segundo nombre.*/
        if(nombre.startsWith("JOSE") || nombre.startsWith("MARIA")){
            String[] divisionNombre = nombre.split(" ");
            segundoNombre = divisionNombre[1]; 
            
            caracteres = segundoNombre.toCharArray();
            curp = curp + caracteres[0];
        }else{
            caracteres = nombre.toCharArray();
            curp = curp + caracteres[0];
        }
        
        //----------------------------------------------------------------------------------
        
        /*Fecha de nacimiento sin espacios en orden de año, mes y día; 
          ejemplo: 010425 (2001, abril 25).*/
        caracteres = añoTexto.toCharArray();
        
        if(añoTexto.length() > 4){
            error = true;
            mensaje = String.valueOf(error);
            return mensaje;
        }
        curp = curp + caracteres[2] + caracteres[3];
        
        //----------------------------------------------------------------------------------
        
        /*mes = mes.toUpperCase().replace("Á", "A")
            .replace("É", "E").replace("Í", "I")
            .replace("Ó", "O").replace("Ú", "U").trim();*/
        switch(mes){
            case 1: mesCurp = "01"; diaMes = 31; break;
            case 2: mesCurp = "02"; diaMes = 29; break;
            case 3: mesCurp = "03"; diaMes = 31; break;
            case 4: mesCurp = "04"; diaMes = 30; break;  
            case 5: mesCurp = "05"; diaMes = 31; break;
            case 6: mesCurp = "06"; diaMes = 30; break;
            case 7: mesCurp = "07"; diaMes = 31; break;
            case 8: mesCurp = "08"; diaMes = 31; break;
            case 9: mesCurp = "09"; diaMes = 30; break;
            case 10: mesCurp = "10"; diaMes = 31; break;
            case 11: mesCurp = "11"; diaMes = 30; break;
            case 12: mesCurp = "12"; diaMes = 31; break;
            /*case "ENERO": mesCurp = "01"; diaMes = 31; break;
            case "FEBRERO": mesCurp = "02"; diaMes = 29; break;
            case "MARZO": mesCurp = "03"; diaMes = 31; break;
            case "ABRIL": mesCurp = "04"; diaMes = 30; break;
            case "MAYO": mesCurp = "05"; diaMes = 31; break;
            case "JUNIO": mesCurp = "06"; diaMes = 30; break;
            case "JULIO": mesCurp = "07"; diaMes = 31; break;
            case "AGOSTO": mesCurp = "08"; diaMes = 31; break;
            case "SEPTIEMBRE": mesCurp = "09"; diaMes = 30; break;
            case "OCTUBRE": mesCurp = "10"; diaMes = 31; break;
            case "NOVIEMBRE": mesCurp = "11"; diaMes = 30; break;
            case "DICIEMBRE": mesCurp = "12"; diaMes = 31; break;*/
            default: error = true; mensaje = String.valueOf(error); return mensaje;
        }
        curp = curp + mesCurp;
        
        //----------------------------------------------------------------------------------
                
        if(dia > 0 && dia <= 9){
            diaTexto = "0" + diaTexto;
        }
        if(dia > diaMes || dia < 1){
            error = true;
            mensaje = String.valueOf(error);
            return mensaje;
            
        }
        curp = curp + diaTexto;
        
        //----------------------------------------------------------------------------------
        
        /*Letra del sexo o género: H para hombre y M para mujer.*/
        if(genero == "Hombre"){ generoTexto = "H"; }
        else if(genero == "Mujer"){ generoTexto = "M"; }
        else{ 
            error = true;
            mensaje = String.valueOf(error);
            return mensaje; 
        }
        curp = curp + generoTexto;
        
        //----------------------------------------------------------------------------------
        
        /*Dos letras correspondientes a la entidad de nacimiento.*/
        switch(entidad){
            case"Aguascalientes": entidadTexto = "AS"; break;
            case"Baja California": entidadTexto = "BC"; break;
            case"Baja California Sur": entidadTexto = "BS"; break;
            case"Campeche": entidadTexto = "CC"; break;
            case"Coahuila de Zaragoza": entidadTexto = "CL"; break;
            case"Colima": entidadTexto = "CM"; break;
            case"Chiapas": entidadTexto = "CS"; break;
            case"Chihuahua": entidadTexto = "CH"; break;
            case"Distrito Federal": entidadTexto = "DF"; break;
            case"Durango": entidadTexto = "DG"; break;
            case"Guanajuato": entidadTexto = "GT"; break;
            case"Guerrero": entidadTexto = "GR"; break;
            case"Hidalgo": entidadTexto = "HG"; break;
            case"Jalisco": entidadTexto = "JC"; break;
            case"México": entidadTexto = "MC"; break;
            case"Michoacán de Ocampo": entidadTexto = "MN"; break;
            case"Morelos": entidadTexto = "MS"; break;
            case"Nayarit": entidadTexto = "NT"; break;
            case"Nuevo León": entidadTexto = "NL"; break;
            case"Oaxaca": entidadTexto = "OC"; break;
            case"Puebla": entidadTexto = "PL"; break;
            case"Querétaro": entidadTexto = "QT"; break;
            case"Quintana Roo": entidadTexto = "QR"; break;
            case"San Luis Potosí": entidadTexto = "SP"; break;
            case"Sinaloa": entidadTexto = "SL"; break;
            case"Sonora": entidadTexto = "SR"; break;
            case"Tabasco": entidadTexto = "TC"; break;
            case"Tamaulipas": entidadTexto = "TS"; break;
            case"Tlaxcala": entidadTexto = "TL"; break;
            case"eracruz de Ignacio de la Llave": entidadTexto = "VZ"; break;
            case"Yucatán": entidadTexto = "YN"; break;
            case"Zacatecas": entidadTexto = "ZS"; break;
            case"Nacido en el Extranjero": entidadTexto = "NE"; break;
            default: error = true; mensaje = String.valueOf(error); return mensaje;
        }
        curp = curp + entidadTexto;
        
        //----------------------------------------------------------------------------------
        
        /*Primera consonante interna (no inicial) del primer apellido.*/
        caracteres = paterno.toCharArray();
        for(int x=1; x<caracteres.length; x++){
            
            if(caracteres[x] != 'A' && caracteres[x] != 'E' && 
               caracteres[x] != 'I' && caracteres[x] != 'O' && caracteres[x] != 'U'){
                    
                curp = curp + caracteres[x];              
                break;
            }
        }
        
        //----------------------------------------------------------------------------------
        
        /*Primera consonante interna (no inicial) del segundo apellido. 
          En caso de no tener segundo apellido, se colocará X.*/
        caracteres = materno.toCharArray();           
        if (caracteres.length == 0){ 
            curp = curp + "X"; 
        }
        
        for(int x=1; x<caracteres.length; x++){
            
            if(caracteres[x] != 'A' && caracteres[x] != 'E' && 
               caracteres[x] != 'I' && caracteres[x] != 'O' && caracteres[x] != 'U'){
                    
                curp = curp + caracteres[x];
                break;
            }
        }
        
        //----------------------------------------------------------------------------------
        
        /*Primera consonante interna (no inicial) del nombre.*/
        
        if(nombre.startsWith("JOSE") || nombre.startsWith("MARIA")){
            caracteres = segundoNombre.toCharArray();
            for(int x=1; x<caracteres.length; x++){
            
                if(caracteres[x] != 'A' && caracteres[x] != 'E' && 
                    caracteres[x] != 'I' && caracteres[x] != 'O' && caracteres[x] != 'U'){
                    
                    curp = curp + caracteres[x];
                    break;
                }
            }
        }
        else{
            caracteres = nombre.toCharArray();
            for(int x=1; x<caracteres.length; x++){
            
                if(caracteres[x] != 'A' && caracteres[x] != 'E' && 
                    caracteres[x] != 'I' && caracteres[x] != 'O' && caracteres[x] != 'U'){
                    
                    curp = curp + caracteres[x];
                    break;
                }
            }
        }
        
        //----------------------------------------------------------------------------------
        
        /*Dígito de 0 para fechas de nacimiento hasta el año 1999 
          y A para fechas de nacimiento del 2000 en adelante.
            Número 1*/
        if(año < 2000){
            digitoFinal = "01";
        }else { digitoFinal = "A1"; }
        curp = curp + digitoFinal;
        
        }catch(Exception err){
            error = true;
            mensaje = String.valueOf(error);
            return mensaje;
            
        }
    return curp;
    }
}
