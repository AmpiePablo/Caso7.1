package Logica;
import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.concurrent.ThreadLocalRandom;
/**
 * laita
 * @author Pablo Ampie
 * 29dh120_dk1_3
 */
public class AES {
   // private ArrayList<Integer> numeros;
    private static ArrayList<String> abecedario;
    private static SecretKeySpec secretKey;
    private static ArrayList<Letra> total;
    private static ArrayList<ArrayList<Letra>> subConjuntosLetras=new ArrayList<ArrayList<Letra>>();
    private static ArrayList<ArrayList<Numero>> subConjuntosNumeros=new ArrayList<ArrayList<Numero>>();
    private static ArrayList<Numero> numeros ;
    private static String secretKeyActual="29dh120_dk1_3";
    private static String textoEncriptado="xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
    
    AES(){
        
    }
    
/*Description:
Dado el siguiente c�digo para definir una llave de AES
configurado com
o AES/ECB/PKCS5Padding,
private static SecretKeySpec secretKey;
*/
public static void setKey(String myKey) throws UnsupportedEncodingException  {
  MessageDigest sha = null;
  try {
      byte[] key = myKey.getBytes("UTF-8");
    sha = MessageDigest.getInstance("SHA-1");
    key = sha.digest(key);
    key = Arrays.copyOf(key, 16);
    secretKey = new SecretKeySpec(key, "AES");
  }
  catch (NoSuchAlgorithmException e) {
    e.printStackTrace();
  }
  catch (UnsupportedEncodingException e) {
    e.printStackTrace();
  }

}
  public static String decrypt(String strToDecrypt, String secret)  {
    try {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      String nuevo=new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
      return nuevo;
      
    }
    catch (Exception e) {
      
    }
    return null;
  }
  
  public static String encrypt(String strToEncrypt, String secret)  {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
  
  
  public static void main(String args[]){
      String textoEncriptada="xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
      tomaLetrasNumeros();
      sacarPosibilidades();
      //imprimir();
      imprimir2();
     // System.out.println(completaPalabra("h",7,"29dh120_dk1_3"));
      /*String decryptedString = AES.decrypt(textoEncriptada, "29dh120_dk1_3") ;
      if(decryptedString==null){
          System.out.println("laita");
      }
      System.out.println(decryptedString); 
       */
  }

  public static void tomaLetrasNumeros(){
      String abecedario[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","�","o","p","q","r","s","t","u","v","w","x","y","z"};
      total=new ArrayList<Letra>();
      for(int i=0;i<abecedario.length;i++){
          Letra nueva=new Letra(abecedario[i]);
          total.add(nueva);
      }
      int actual[]={0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
      numeros=new ArrayList<Numero>();
      for(int j=0;j<actual.length;j++){
          Numero nuevo=new Numero(actual[j]);
          numeros.add(nuevo);
      }
  }
  public static String completaPalabra(String pLetra, int pNumero,String pPalabra){
      String datoNum=Integer.toString(pNumero);
      String valorRetorno="";
      int cont=0;
      for(int i=0;i<pPalabra.length();i++){
        if(pPalabra.charAt(i)=='_'){
            if(cont==0){
                valorRetorno+=pLetra;
                cont++;
            }else {
                valorRetorno+=datoNum;
            }
        }else{
            valorRetorno+=pPalabra.charAt(i);
        }
      }
      return valorRetorno;
      
  }
  
  public static void sacarPosibilidades(){
    int cantAciertos=0;
    while(cantAciertos<25){
        ArrayList<Letra> actualLetra=tomaSubConjunto();
        ArrayList<Numero> actualNum=tomaSubConjuntoNum();
        int aciertosTemp=0;
        for(int i=0;i<actualLetra.size();i++){
            String copia=secretKeyActual;
            int letraActual = ThreadLocalRandom.current().nextInt(0,3);
            int numActual = ThreadLocalRandom.current().nextInt(0,3);
            copia=completaPalabra(actualLetra.get(letraActual).getLetra(),actualNum.get(numActual).getNumero(),secretKeyActual);
            String decryptedString=AES.decrypt(textoEncriptado, copia) ;
            if(decryptedString!=null){
                actualLetra.get(letraActual).sumarPosibilidad();
                actualNum.get(numActual).sumarPosibilidad();
                cantAciertos++;
                aciertosTemp++;
            } 
        }
        if(aciertosTemp>0){
            subConjuntosLetras.add(actualLetra);
            subConjuntosNumeros.add(actualNum);
            aciertosTemp=0;
        }else{
            aciertosTemp=0;
        }
        
    }
    System.out.println(cantAciertos);
  }
  public static ArrayList<Letra> tomaSubConjunto(){
      ArrayList<Letra> subConjunto=new ArrayList<Letra>();
      for(int i=0;i<4;i++){
        int letraActual = ThreadLocalRandom.current().nextInt(0,26+1);
        subConjunto.add(total.get(letraActual));
      }
      return subConjunto;
  }
  public static ArrayList<Numero> tomaSubConjuntoNum(){
      ArrayList<Numero> subConjunto=new ArrayList<Numero>();
      for(int i=0;i<4;i++){
        int numActual = ThreadLocalRandom.current().nextInt(0,9+1);
        subConjunto.add(numeros.get(numActual));
      }
      return subConjunto; 
  }
  
  
  
  public static int buscaPosLetra(String dato){
    int posicion = -1;
    for(int i = 0; i < total.size(); i++){
      if(total.get(i).getLetra().equals(dato)){
        posicion = i;
        break;
       }
    }
    return posicion;
  }
  public static int buscaPosNum(int dato){
    int posicion = -1;
    for(int i = 0; i < numeros.size(); i++){
      if(numeros.get(i).getNumero()==dato){
        posicion = i;
        break;
       }
    }
    return posicion;
  }
  
  
  public static void imprimir(){
      for(int i=0;i<total.size();i++){
          System.out.println(total.get(i).getLetra()+" - "+total.get(i).getPosibilidadAcierto());
      }
      System.out.println("Numeros: ");
      for(int j=0;j<numeros.size();j++){
          System.out.println(numeros.get(j).getNumero()+" - "+numeros.get(j).getPosibilidad());
      }
  }
  
  public static void imprimir2(){
      for(int i=0;i<subConjuntosLetras.size();i++){
          for(int j=0;j<subConjuntosLetras.get(i).size();j++){
              System.out.println(subConjuntosLetras.get(i).get(j).getLetra()+" - "+subConjuntosLetras.get(i).get(j).getPosibilidadAcierto());
          }
          System.out.println("siguiente SubConjunto de Letras");
      }
      for(int i=0;i<subConjuntosNumeros.size();i++){
          for(int j=0;j<subConjuntosNumeros.get(i).size();j++){
              System.out.println(subConjuntosNumeros.get(i).get(j).getNumero()+" - "+subConjuntosNumeros.get(i).get(j).getPosibilidad());
          }
          System.out.println("siguiente SubConjunto de Numeros");
      }
  }
}