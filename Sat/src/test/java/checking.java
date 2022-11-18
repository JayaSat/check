import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class checking extends Clickthrogh {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//Clickthrogh b=new Clickthrogh();
		//b.base();
		//b.ComOnline("https://originlearning.in/Wabtec_HTML5/2021/v228_DE_Basic/index.html","DEB", 5, 5.);
	
		final BufferedImage image = ImageIO.read(new File("C:\\Users\\vijay\\Pictures\\two.jpg"));

	 
	    Font font = new Font("Arial", Font.BOLD, 18);

	    Graphics g = image.getGraphics();
	    g.setFont(font);
	    g.setColor(Color.RED);
	    g.drawString("Try to get text fkjsdkfmlm kfnfmdnfs  dkfskdjfnsdf   dfkjsdkflsjdf  dksfsjdkffkdf",0 , 600);
	    
	    
	    
	    ImageIO.write(image, "png", new File("C:\\Users\\vijay\\Pictures\\Check1.png"));
	
	
	
	
	}

}
