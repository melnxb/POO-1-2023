/*Grupo 18 - Pjbl Ciência da computação
 * Kauany Almeida Silva
 * Melyça Neres Bortolleto Colaço
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.File;
import java.awt.Desktop;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;


abstract class ObjetoGrafico implements Serializable{
    private int x;
    private int y;
    private int radius;
    private Color color;

    public ObjetoGrafico(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }
    public abstract void draw(Graphics g);

}
//draw para inimigo e comida
//classe jogador
//diferenciar jogador/inimigo de comida 
//

class Jogador extends ObjetoGrafico {
    public Jogador jogador;

    public Jogador(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }
    
    public void draw(Graphics g){
            g.setColor(getColor());
            g.fillOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);
    }

}

class Inimigo extends ObjetoGrafico {
    private int x_inicial;
    private int y_inicial;
    private int velocidade_x;
    private int velocidade_y;
    private boolean movendo_direito;
    private boolean movendo_pbaixo;

    public Inimigo(int x, int y, int radius, Color color, int velocidade_x, int velocidade_y) {
        super(x, y, radius, color);
        this.x_inicial = x;
        this.y_inicial = y;
        this.velocidade_x = velocidade_x;
        this.velocidade_y = velocidade_y;
        this.movendo_direito = true;
        this.movendo_pbaixo = true;
    }

    public void moverZigueZague() {
        if (movendo_direito) {
            setX(getX() + velocidade_x);

            if (getX() >= x_inicial + 218) {
                movendo_direito = false;
            }
        } else {
            setX(getX() - velocidade_x);

            if (getX() <= x_inicial) {
                movendo_direito = true;
            }
        }

        if (movendo_pbaixo) {
            setY(getY() + velocidade_y);

            if (getY() >= y_inicial + 121) {
                movendo_pbaixo = false;
            }
        } else {
            setY(getY() - velocidade_y);

            if (getY() <= y_inicial) {
                movendo_pbaixo = true;
            }
        }

    }
    public void draw(Graphics g){
        g.setColor(getColor());
        g.fillOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);
        }
    }


class Comida extends ObjetoGrafico {

    public Comida(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }
    public void draw(Graphics g){
        g.setColor(getColor());
        g.fillOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);
    }
    //draw 
}

class Jogo extends JPanel implements KeyListener {
    private ArrayList<ObjetoGrafico> listaCircles;
    private ArrayList<Inimigo> listaInimigos;
    private ArrayList<ObjetoGrafico> listaComidas;
    public Jogador jogador;
    public int pontuacao;
    private boolean gameOver;
    private boolean vencedor;
    private Rectangle quadradoCINZA;
    private Rectangle quadradoVERDE;

    private boolean paused;
    
    private boolean arquivoAberto = false;


    public Jogo() {

        listaCircles = new ArrayList<>();
        listaInimigos = new ArrayList<>();
        listaComidas = new ArrayList<>();

        Jogador jogador = new Jogador(58, 620, 40, Color.YELLOW);
        listaCircles.add(jogador);
        this.jogador = jogador;

        Comida comida1 = new Comida(130, 620, 9, Color.BLUE); listaCircles.add(comida1); listaComidas.add(comida1); Comida comida2 = new Comida(190, 620, 9, Color.YELLOW); listaCircles.add(comida2); listaComidas.add(comida2);
        Comida comida3 = new Comida(250, 620, 9, Color.BLUE); listaCircles.add(comida3); listaComidas.add(comida3); Comida comida4 = new Comida(310, 620, 9, Color.YELLOW); listaCircles.add(comida4); listaComidas.add(comida4);
        Comida comida5 = new Comida(370, 620, 9, Color.BLUE); listaCircles.add(comida5); listaComidas.add(comida5); Comida comida6 = new Comida(430, 620, 9, Color.YELLOW); listaCircles.add(comida6); listaComidas.add(comida6);
        Comida comida7 = new Comida(490, 620, 9, Color.BLUE); listaCircles.add(comida7); listaComidas.add(comida7); Comida comida8 = new Comida(550, 620, 9, Color.YELLOW); listaCircles.add(comida8); listaComidas.add(comida8); 
        Comida comida9 = new Comida(610, 620, 9, Color.BLUE); listaCircles.add(comida9); listaComidas.add(comida9); Comida comida10 = new Comida(670, 620, 9, Color.YELLOW); listaCircles.add(comida10); listaComidas.add(comida10);
        Comida comida11 = new Comida(730, 620, 9, Color.BLUE); listaCircles.add(comida11); listaComidas.add(comida11); Comida comida12 = new Comida(790, 620, 9, Color.YELLOW); listaCircles.add(comida12); listaComidas.add(comida12);
        Comida comida13 = new Comida(850, 620, 9, Color.BLUE); listaCircles.add(comida13); listaComidas.add(comida13); Comida comida14 = new Comida(910, 620, 9, Color.YELLOW); listaCircles.add(comida14); listaComidas.add(comida14);
        Comida comida15 = new Comida(970, 620, 9, Color.BLUE); listaCircles.add(comida15); listaComidas.add(comida15); Comida comida16 = new Comida(1070, 620, 9, Color.BLUE); listaCircles.add(comida16); listaComidas.add(comida16); 
        // quadrado cinza aqui
        Comida comida17 = new Comida(1130, 620, 9, Color.YELLOW); listaCircles.add(comida17); listaComidas.add(comida17); Comida comida18 = new Comida(1190, 620, 9, Color.BLUE); listaCircles.add(comida17); listaComidas.add(comida17);
        Comida comida19 = new Comida(1250, 620, 9, Color.YELLOW); listaCircles.add(comida18); listaComidas.add(comida18); Comida comida20 = new Comida(1310, 620, 9, Color.BLUE); listaCircles.add(comida19); listaComidas.add(comida19);
        // um inimigo aqui

        Comida comida21 = new Comida(990, 540, 9, Color.BLUE); listaCircles.add(comida21); listaComidas.add(comida21); Comida comida22 = new Comida(990, 505, 9, Color.YELLOW); listaCircles.add(comida22); listaComidas.add(comida22);
        Comida comida23 = new Comida(930, 505, 9, Color.BLUE); listaCircles.add(comida23); listaComidas.add(comida23); Comida comida24 = new Comida(870, 505, 9, Color.YELLOW); listaCircles.add(comida24); listaComidas.add(comida24);
        Comida comida25 = new Comida(810, 505, 9, Color.BLUE); listaCircles.add(comida25); listaComidas.add(comida25);
        // linha indo pra esquerda
        Comida comida26 = new Comida(1150, 505, 9, Color.YELLOW); listaCircles.add(comida26); listaComidas.add(comida26); Comida comida27 = new Comida(1210, 505, 9, Color.BLUE); listaCircles.add(comida27); listaComidas.add(comida27);
        Comida comida28 = new Comida(1270, 505, 9, Color.YELLOW); listaCircles.add(comida28); listaComidas.add(comida28); Comida comida29 = new Comida(1328, 505, 9, Color.BLUE); listaCircles.add(comida29); listaComidas.add(comida29);
        // superior direito
        Comida comida30 = new Comida(1328, 555, 9, Color.YELLOW); listaCircles.add(comida30); listaComidas.add(comida30); Comida comida31 = new Comida(1328, 620, 9, Color.BLUE); listaCircles.add(comida31); listaComidas.add(comida31);
        Comida comida32 = new Comida(1050, 505, 9, Color.YELLOW); listaCircles.add(comida32); listaComidas.add(comida32); Comida comida33 = new Comida(1110, 505, 9, Color.BLUE); listaCircles.add(comida33); listaComidas.add(comida33);
        
        // penultima linha indo pra direita
        Comida comida34 = new Comida(750, 505, 9, Color.blue); listaCircles.add(comida34); listaComidas.add(comida34); Comida comida35 = new Comida(690, 505, 9, Color.YELLOW); listaCircles.add(comida35); listaComidas.add(comida35);
        Comida comida36 = new Comida(630, 505, 9, Color.BLUE); listaCircles.add(comida36); listaComidas.add(comida36); Comida comida37 = new Comida(570, 505, 9, Color.YELLOW); listaCircles.add(comida37); listaComidas.add(comida37);
        Comida comida38 = new Comida(510, 505, 9, Color.BLUE); listaCircles.add(comida38); listaComidas.add(comida38); Comida comida42 = new Comida(450, 505, 9, Color.YELLOW); listaCircles.add(comida42); listaComidas.add(comida42);
        Comida comida39 = new Comida(390, 505, 9, Color.BLUE); listaCircles.add(comida39); listaComidas.add(comida39); Comida comida40 = new Comida(330, 505, 9, Color.YELLOW); listaCircles.add(comida40); listaComidas.add(comida40);
        Comida comida41 = new Comida(290, 505, 9, Color.BLUE); listaCircles.add(comida41); listaComidas.add(comida41); Comida comida43 = new Comida(220, 505, 9, Color.YELLOW); listaCircles.add(comida43); listaComidas.add(comida43);
        Comida comida44 = new Comida(140, 505, 9, Color.BLUE); listaCircles.add(comida44); listaComidas.add(comida44); Comida comida45 = new Comida(70, 505, 9, Color.YELLOW); listaCircles.add(comida45); listaComidas.add(comida45);
        // penultima linha vertical
        Comida comida46 = new Comida(70, 395, 9, Color.BLUE); listaCircles.add(comida46); listaComidas.add(comida46); Comida comida47 = new Comida(130, 395, 9, Color.YELLOW); listaCircles.add(comida47); listaComidas.add(comida47);
        Comida comida48 = new Comida(190, 395, 9, Color.BLUE); listaCircles.add(comida48); listaComidas.add(comida48); Comida comida49 = new Comida(250, 395, 9, Color.YELLOW); listaCircles.add(comida49); listaComidas.add(comida49);
        Comida comida50 = new Comida(310, 395, 9, Color.BLUE); listaCircles.add(comida50); listaComidas.add(comida50); Comida comida51 = new Comida(370, 395, 9, Color.yellow); listaCircles.add(comida51); listaComidas.add(comida51);
        Comida comida52 = new Comida(430, 395, 9, Color.BLUE); listaCircles.add(comida52); listaComidas.add(comida52); Comida comida53 = new Comida(490, 395, 9, Color.YELLOW); listaCircles.add(comida53); listaComidas.add(comida53);
        Comida comida54 = new Comida(550, 395, 9, Color.BLUE); listaCircles.add(comida54); listaComidas.add(comida54); Comida comida55 = new Comida(610, 395, 9, Color.YELLOW); listaCircles.add(comida55); listaComidas.add(comida55); 
        Comida comida56 = new Comida(670, 395, 9, Color.BLUE); listaCircles.add(comida56); listaComidas.add(comida56); Comida comida57 = new Comida(730, 395, 9, Color.YELLOW); listaCircles.add(comida57); listaComidas.add(comida57);
        Comida comida58 = new Comida(790, 395, 9, Color.BLUE); listaCircles.add(comida58); listaComidas.add(comida58); Comida comida59 = new Comida(850, 395, 9, Color.YELLOW); listaCircles.add(comida59); listaComidas.add(comida59);
        Comida comida60 = new Comida(910, 395, 9, Color.BLUE); listaCircles.add(comida60); listaComidas.add(comida60); Comida comida61 = new Comida(970, 395, 9, Color.YELLOW); listaCircles.add(comida61); listaComidas.add(comida61);
        Comida comida62 = new Comida(1030, 395, 9, Color.BLUE); listaCircles.add(comida62); listaComidas.add(comida62); Comida comida63 = new Comida(1090, 395, 9, Color.YELLOW); listaCircles.add(comida63); listaComidas.add(comida63); 
        Comida comida64 = new Comida(1150, 395, 9, Color.BLUE); listaCircles.add(comida64); listaComidas.add(comida64); Comida comida65 = new Comida(1210, 395, 9, Color.YELLOW); listaCircles.add(comida65); listaComidas.add(comida65);
        Comida comida66 = new Comida(1270, 395, 9, Color.BLUE); listaCircles.add(comida66); listaComidas.add(comida66); Comida comida67 = new Comida(1330, 395, 9, Color.YELLOW); listaCircles.add(comida67); listaComidas.add(comida67);
        Comida comida68 = new Comida(1330, 395, 9, Color.YELLOW); listaCircles.add(comida68); listaComidas.add(comida68); 
        // linha subindo canto esquerdp
        Comida comida69 = new Comida(370, 290, 9, Color.BLUE); listaCircles.add(comida69); listaComidas.add(comida69); Comida comida70 = new Comida(310, 290, 9, Color.YELLOW); listaCircles.add(comida70); listaComidas.add(comida70); 
        Comida comida71 = new Comida(250, 290, 9, Color.BLUE); listaCircles.add(comida71); listaComidas.add(comida71); Comida comida72 = new Comida(190, 290, 9, Color.YELLOW); listaCircles.add(comida72); listaComidas.add(comida72);
        Comida comida73 = new Comida(130, 290, 9, Color.green); listaCircles.add(comida73); listaComidas.add(comida73); Comida comida74 = new Comida(70, 290, 9, Color.YELLOW); listaCircles.add(comida74); listaComidas.add(comida74);
        // indo pra direita
        Comida comida75 = new Comida(430, 290, 9, Color.YELLOW); listaCircles.add(comida75); listaComidas.add(comida75); Comida comida76 = new Comida(490, 290, 9, Color.BLUE); listaCircles.add(comida76); listaComidas.add(comida76); 
        Comida comida77 = new Comida(550, 290, 9, Color.YELLOW); listaCircles.add(comida77); listaComidas.add(comida77); Comida comida78 = new Comida(610, 290, 9, Color.YELLOW); listaCircles.add(comida78); listaComidas.add(comida78); 
        Comida comida79 = new Comida(670, 290, 9, Color.YELLOW); listaCircles.add(comida79); listaComidas.add(comida79); Comida comida80 = new Comida(730, 290, 9, Color.BLUE); listaCircles.add(comida80); listaComidas.add(comida80); 
        Comida comida81 = new Comida(790, 290, 9, Color.YELLOW); listaCircles.add(comida81); listaComidas.add(comida81); Comida comida82 = new Comida(850, 290, 9, Color.BLUE); listaCircles.add(comida82); listaComidas.add(comida82); 
        Comida comida83 = new Comida(910, 290, 9, Color.YELLOW); listaCircles.add(comida83); listaComidas.add(comida83); Comida comida84 = new Comida(970, 290, 9, Color.BLUE); listaCircles.add(comida84); listaComidas.add(comida84); 
        Comida comida85 = new Comida(1030, 290, 9, Color.YELLOW); listaCircles.add(comida85); listaComidas.add(comida85); Comida comida86 = new Comida(1090, 290, 9, Color.BLUE); listaCircles.add(comida86); listaComidas.add(comida86); 
        Comida comida87 = new Comida(1150, 290, 9, Color.YELLOW); listaCircles.add(comida87); listaComidas.add(comida87); Comida comida88 = new Comida(1210, 290, 9, Color.BLUE); listaCircles.add(comida88); listaComidas.add(comida88); 
        Comida comida89 = new Comida(1270, 290, 9, Color.YELLOW); listaCircles.add(comida89); listaComidas.add(comida89); Comida comida90 = new Comida(1330, 290, 9, Color.BLUE); listaCircles.add(comida90); listaComidas.add(comida90); 
        // ?
        Comida comida91 = new Comida(610, 190, 9, Color.yellow); listaCircles.add(comida91); listaComidas.add(comida91); Comida comida92 = new Comida(550, 190, 9, Color.BLUE); listaCircles.add(comida92); listaComidas.add(comida92); 
        Comida comida93 = new Comida(490, 190, 9, Color.BLUE); listaCircles.add(comida93); listaComidas.add(comida93); Comida comida94 = new Comida(430, 190, 9, Color.YELLOW); listaCircles.add(comida94); listaComidas.add(comida94); 
        Comida comida95 = new Comida(370, 190, 9, Color.BLUE); listaCircles.add(comida95); listaComidas.add(comida95); Comida comida96 = new Comida(310, 190, 9, Color.YELLOW); listaCircles.add(comida96); listaComidas.add(comida96);
        Comida comida97 = new Comida(250, 190, 9, Color.BLUE); listaCircles.add(comida97); listaComidas.add(comida97); Comida comida98 = new Comida(190, 190, 9, Color.YELLOW); listaCircles.add(comida98); listaComidas.add(comida98);
        Comida comida99 = new Comida(130, 190, 9, Color.BLUE); listaCircles.add(comida99); listaComidas.add(comida99); 
        Comida comida100 = new Comida(70, 190, 9, Color.YELLOW); listaCircles.add(comida100); listaComidas.add(comida100); 
        // INDO PRA DIREITA
        Comida comida101 = new Comida(670, 190, 9, Color.BLUE); listaCircles.add(comida101); listaComidas.add(comida101); Comida comida102 = new Comida(730, 190, 9, Color.yellow); listaCircles.add(comida102); listaComidas.add(comida102); 
        Comida comida103 = new Comida(790, 190, 9, Color.BLUE); listaCircles.add(comida103); listaComidas.add(comida103); 
        // subindo primeira linha
        Comida comida104 = new Comida(310, 70, 9, Color.yellow); listaCircles.add(comida104); listaComidas.add(comida104); Comida comida106 = new Comida(370, 70, 9, Color.BLUE); listaCircles.add(comida106); listaComidas.add(comida106);
        Comida comida105 = new Comida(250, 70, 9, Color.BLUE); listaCircles.add(comida105); listaComidas.add(comida105); Comida comida107 = new Comida(190, 70, 9, Color.YELLOW); listaCircles.add(comida107); listaComidas.add(comida107);
        Comida comida108 = new Comida(130, 70, 9, Color.YELLOW); listaCircles.add(comida108); listaComidas.add(comida108);

        pontuacao = 0;
        gameOver = false;
        vencedor = false;

        paused = false;

        criar_inimigos();

        quadradoCINZA = new Rectangle(950, 583, 88, 85);
        quadradoVERDE = new Rectangle(30, 40, 80, 80);

        setFocusable(true);
        addKeyListener(this);

        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pausarOuRetomarJogo(); // Chama o método para pausar ou retomar o jogo
            }
        });
    }
    
    Timer timer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            atualizar_jogo();
        }
    });
    
    public void atualizar_jogo() {
        mexer_inimigos();
        verifica_colisao();
        repaint();
        }
        

    public void pausarOuRetomarJogo() {
        paused = !paused; // Inverte o estado de pausa

        if (paused) {
            JOptionPane.showMessageDialog(null, "Jogo pausado"); // Exibe um diálogo informando que o jogo está pausado
        }

// Redesenha o painel
        /* 
        public atualizarJogo() {
            try {
                // Verificar se o jogador está saindo da tela
                if (getX() - getRadius() < 0) {
                    setX(getRadius()); // Impedir que o jogador saia para a esquerda
                }
                if (getX() + getRadius() > frame.getWidth()) {
                    setX(frame.getWidth() - getRadius()); // Impedir que o jogador saia para a direita
                }
                if (getY() - getRadius() < 0) {
                    setY(getRadius()); // Impedir que o jogador saia para cima
                }
                if (getY() + getRadius() > tela.getHeight()) {
                    setY(Frame.getHeight() - getRadius()); // Impedir que o jogador saia para baixo
                }
            }
                
                // Outras atualizações do jogo...
                
            } catch (SaidaTelaException e) {
                // Lidar com a saída da tela aqui
                System.out.println(e.getMessage());
                // Restaurar o jogador para uma posição válida
                setX(0);
                setY(0);
            }
    }
    */

 //new
    private void criar_inimigos() {
        Inimigo inimigo1 = new Inimigo(40, 180, 28, Color.GREEN, 3, 4); listaInimigos.add(inimigo1);
        Inimigo inimigo2 = new Inimigo(700, 80, 28, Color.RED, 2, 5); listaInimigos.add(inimigo2);
        Inimigo inimigo3 = new Inimigo(423, 381, 28, Color.RED, 5, 3); listaInimigos.add(inimigo3);
        Inimigo inimigo4 = new Inimigo(1038, 445, 28, Color.GREEN, 6, 3); listaInimigos.add(inimigo4);
    }

    public void jogar() {
        JFrame frame = new JFrame("Jogo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1355, 725);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }


    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.BLACK);

        for (ObjetoGrafico comida : listaCircles) {
            comida.draw(g);
        }

        for (Inimigo inimigo : listaInimigos) {
            inimigo.draw(g);
        }

        g.setColor(Color.BLUE);
        g.fillRect(quadradoCINZA.x, quadradoCINZA.y, quadradoCINZA.width, quadradoCINZA.height);

        g.setColor(Color.GREEN);
        g.fillRect(quadradoVERDE.x, quadradoVERDE.y, quadradoVERDE.width, quadradoVERDE.height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 19));
        g.drawString("Pontuação: " + pontuacao, 488, 43);
        g.setFont(new Font("Verdana", Font.BOLD, 15));
        g.drawString("Bem-vindo ao Jogo!", 910,45);
        g.drawString("Para movimentar o jogador", 910, 65);
        g.drawString("utilize as teclas de setas:", 910, 85);
        g.drawString("\u2192 Mover para a direita", 910, 105);  // Seta para a direita
        g.drawString("\u2191 Mover para cima", 1100, 105);      // Seta para cima
        g.drawString("\u2190 Mover para a esquerda", 910, 125); // Seta para a esquerda
        g.drawString("\u2193 Mover para baixo", 1123, 125);     // Seta para baixo
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Verdana", Font.BOLD, 17));
        g.drawString("Ciência da Computação 3A", 925, 180);
        g.setColor(Color.white);
        g.drawString("Kauany Almeida Silva", 939, 208);
        g.drawString("Melyça Neres Bortoletto", 939, 230);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 13));
        g.drawString("Instruções", 955, 628);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 16));
        g.drawString("Saída!", 45, 85);
    }

    public void mexer_inimigos() {
        for (Inimigo inimigo : listaInimigos) {
            inimigo.moverZigueZague();
        }

        verifica_colisao();
        repaint();
    }

    
    private void verifica_colisao() {
        Rectangle jogadorBounds = new Rectangle(
            jogador.getX() - jogador.getRadius(),
            jogador.getY() - jogador.getRadius(),
            jogador.getRadius() * 2,
            jogador.getRadius() * 2
        );

        for (int i = 0; i < listaComidas.size(); i++) {
            ObjetoGrafico comida = listaComidas.get(i);

            Rectangle comidasBounds = new Rectangle(
                comida.getX() - comida.getRadius(),
                comida.getY() - comida.getRadius(),
                comida.getRadius() * 2,
                comida.getRadius() * 2
            );

            if (jogadorBounds.intersects(comidasBounds)) {
                pontuacao += 1;
                listaComidas.remove(comida);
                listaCircles.remove(comida);
                i--;
            }
        }
        Rectangle quadradoCINZA_Bounds = new Rectangle(
            quadradoCINZA.x - 20,
            quadradoCINZA.y - 20,
            quadradoCINZA.width,
            quadradoCINZA.height
        );

        Rectangle quadradoVERDE_Bounds = new Rectangle(
            quadradoVERDE.x - 20,  // quanto ele 'encosta' no circulo amarelo
            quadradoVERDE.y - 20,
            quadradoVERDE.width,
            quadradoVERDE.height
        );


        if (jogadorBounds.intersects(quadradoVERDE_Bounds)) {
            pontuacao += 10;
            vencedor = true;
        }
        //finally impede de continuar abrindo continuamente o arquivo
        if (jogadorBounds.intersects(quadradoCINZA_Bounds) && !arquivoAberto) {
            try {
                File arquivo = new File("instrucoes.txt");
                Desktop.getDesktop().open(arquivo);
                arquivoAberto = true;
                Thread.sleep(1600); // é para dar tempo de sair de instrucoes e nao abrir varios arquivos infinitamente.
                jogador.setX(jogador.getX() + 10);  // Deslocamento de 10 pixels
                jogador.setY(jogador.getY() + 10);  // Deslocamento de 10 pixels            
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    jogador.setX(jogador.getX() - 30);  // pra nao abrir varios arquivos
                    jogador.setY(jogador.getY() - 30); 
                    arquivoAberto = true;
                }
        }
        

        if (vencedor) {
            vencedor = false;
            JOptionPane.showMessageDialog(null, "Parabéns! Você venceu!\nBônus de pontuação: +10\n\n Pontuação final: " + pontuacao);
            System.exit(0);
        }
        

        for (Inimigo inimigo : listaInimigos) {
            Rectangle redBounds = new Rectangle(
                inimigo.getX() - inimigo.getRadius() + 7,
                inimigo.getY() - inimigo.getRadius() + 7,
                inimigo.getRadius() * 2,
                inimigo.getRadius() * 2
            );
            if (jogadorBounds.intersects(redBounds)) {
                gameOver = true;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            jogador.setY(jogador.getY() - 20);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            jogador.setY(jogador.getY() + 20);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            jogador.setX(jogador.getX() - 20);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            jogador.setX(jogador.getX() + 20);
        }
    }
    

    public void keyTyped(KeyEvent e) {
        // Não utilizado
    }
    public void keyReleased(KeyEvent e) {
        // Não utilizado
    }
    public boolean game_over() {
        return gameOver;
    }

    public static void main(String[] args) {
        Jogo game = new Jogo();
        game.jogar();

        while (!game.game_over()) {
            game.mexer_inimigos();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JOptionPane.showMessageDialog(null, "Você perdeu! Pontuação: " + game.pontuacao);
        System.exit(0);
    }
}