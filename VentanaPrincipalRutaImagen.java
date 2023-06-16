import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.swing.*;

import java.awt.image.*;

public class VentanaPrincipalRutaImagen extends JFrame implements ActionListener{
	public JMenuItem salir1;
	public JComboBox comboImagen;
	public JLabel labelImagen;
	public String []nombreImagen = {"","peter","payaso","beyonce","winnie","dinosaurio","gatibu","koala","uno"};
	public CargarPixelImagen cpi = new CargarPixelImagen();	
	public Image imagenOriginal,imagen1;
	public boolean si = false;
	public Panel panel;
	public int insetArriba;
	public int insetIzqda;
	public int ancho,alto;
	public VentanaPrincipalRutaImagen(){
		super();
		setTitle("Tratamiento de Imagenes");
		setSize(950,550);
		setLocation(50,30);
	
		salir1 = new JMenuItem("Salir");
		salir1.setEnabled(true);
		salir1.addActionListener(this);

		labelImagen = new JLabel("Seleccionar Imagen: ");
		labelImagen.setBounds(10,30,140,25);
		add(labelImagen);
		comboImagen = new JComboBox();
		comboImagen.setBounds(140,30,140,25);
		comboImagen.addActionListener(this);
		add(comboImagen);
		
		for(int i = 0; i<9;i++){
			comboImagen.addItem(nombreImagen[i]);
		}
		panel = new Panel();
		add(panel,BorderLayout.CENTER);

		this.setResizable(false);
		setVisible(true);
		
	}
	class Panel extends JPanel{
		public void paint(Graphics g){
			if( imagenOriginal != null ) {
			      g.drawImage( imagenOriginal,insetIzqda+20,insetArriba+80,this );
			}
			if(si){
				g.drawImage( imagen1,insetIzqda+240,insetArriba+80,this );
			}
		}
	}
	/*eventos de la accion de caca boton
	 */
	public void actionPerformed(ActionEvent e) {
		if( comboImagen.getSelectedItem().toString().compareTo("")!=0){
			///home/costa/Escritorio/IMAGEN/WORSPACE/RGBimagen/imagen
			String ruta = "/home/costa/Escritorio/IMAGEN/WORSPACE/RGBimagen/imagen/"+comboImagen.getSelectedItem().toString()+".jpg";
			System.out.println(ruta);
			//String ruta = "imagen\\"+comboImagen.getSelectedItem().toString()+".jpg";
			imagenOriginal = Toolkit.getDefaultToolkit().getImage( "/home/costa/Escritorio/IMAGEN/WORSPACE/RGBimagen/imagen/"+comboImagen.getSelectedItem().toString()+".jpg" );
		    MediaTracker tracker = new MediaTracker( this );
		    tracker.addImage( imagenOriginal,1 );
		    imagen1 = imagenOriginal;
		    insetArriba = this.getInsets().top;
		    insetIzqda = this.getInsets().left; 
			cpi.cargarImagen(ruta);
			labelImagen.setVisible(true);
			repaint();
		}
		if(e.getSource().equals(salir1)){
			salirSistema();
		}
		///home/costa/Escritorio/IMAGEN/WORSPACE/RGBimagen/imagen/koala.jpg
	}
	
	//funcion principal
	public static void main(String[] args) {
		VentanaPrincipalRutaImagen i = new VentanaPrincipalRutaImagen();
	}
	
	public void salirSistema(){
		System.exit(1);
	}
}
