package JuegoJava;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.InputStream;

public class Cancha extends JPanel {
	Pelota pelota = new Pelota(this);
	Raqueta raqueta = new Raqueta(this);
	private Image fondo; // Variable para almacenar la imagen de fondo

	public Cancha() {
		// Cargar la imagen de fondo
		try (InputStream inputStream = getClass().getResourceAsStream("/assets/fondo.jpg")) {
			if (inputStream != null) {
				fondo = ImageIO.read(inputStream);
			} else {
				System.err.println("La imagen de fondo no se pudo encontrar.");
			}
		} catch (IOException e) {
			e.printStackTrace(); // Manejo de errores si la imagen no se encuentra
		}

		// Configuración del KeyListener
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// No hace nada
			}

			@Override
			public void keyReleased(KeyEvent e) {
				raqueta.keyReleassed(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				raqueta.keyPressed(e);
			}
		});

		setFocusable(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics2d = (Graphics2D) g;

		// Dibujar la imagen de fondo
		if (fondo != null) {
			graphics2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this); // Dibuja la imagen de fondo
		}

		// Dibujar la pelota y la raqueta
		pelota.pintarPelota(graphics2d);
		raqueta.pintarRaqueta(graphics2d);
	}

	public void mover() {
		pelota.moverPelota();
		raqueta.moverRaqueta();
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Se terminó el juego", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
}

