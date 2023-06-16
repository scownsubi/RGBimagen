import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.*;

public class CargarPixelImagen extends JPanel{
	 public Image imagenOriginal;               
	 public int anchoImagen; 
	 public int altoImagen;
	 public int []pixelImagen;
	 public int r=0, g=0, b=0;
	CargarPixelImagen(){
	}
	public void cargarImagen(String ruta){
		imagenOriginal = Toolkit.getDefaultToolkit().getImage( ruta ); 
		MediaTracker tracker = new MediaTracker( this ); 
		tracker.addImage( imagenOriginal,1 ); 
		try {
			if( !tracker.waitForID( 1,10000 ) ) { 
				System.out.println( "Error en la carga de la imagen" ); 
		        System.exit( 1 );
		    } 
		} 
		catch( InterruptedException e ) { 
		     System.out.println( e ); 
		} 
		anchoImagen = imagenOriginal.getWidth( this ); 
		altoImagen = imagenOriginal.getHeight( this ); 
		pixelImagen = new int[anchoImagen * altoImagen]; 
		try{ 
			PixelGrabber pgObj = new PixelGrabber( imagenOriginal,0,0,anchoImagen,altoImagen,pixelImagen,0,anchoImagen );
			if( pgObj.grabPixels() && ( (pgObj.getStatus() & ImageObserver.ALLBITS ) != 0 ) ) { 
				try{
					FileWriter ficheroImagen = new FileWriter("archivoImagen\\imagenPixel.txt");
					PrintWriter printImagen = new PrintWriter(ficheroImagen);
					for (int i=0; i < (anchoImagen*altoImagen); i++){
						int pixel=pixelImagen[i];
						//if((i % 12 == 0 )&& (i > 0)){
						//	printImagen.print("\n");
						//}
						b = pixel & 255;
						printImagen.print("("+b+",");
						pixel = pixel >> 8;
						g = pixel & 255;
						printImagen.print(g+",");
						pixel = pixel >>8;
						r = pixel & 255;
						printImagen.print(r+")");
						printImagen.print("\n");
						
						System.out.println(b+" "+g+" "+r);
					}
					
					printImagen.close();
				} 
				catch (Exception e){
					e.printStackTrace();
				}
				
			}
			else { 
				System.out.println( "Problemas al descomponer la imagen" ); 
			}
		} 
		catch( Exception e ){ 
			System.out.println( e ); 
		} 
	}
}
