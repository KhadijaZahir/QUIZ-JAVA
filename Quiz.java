package QuizApplication;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener {
	String[] questions = {
			"JAVA est  un langage",
			"Toutes les classes héritent de la classe",
			"Par convention une classe ",
			"Est-ce que on peut avoir plusieurs constructeurs pour la même classe",
			"Dans la ligne 'public class A implements B', B est : ",
			" ",
			"Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecode Quelle est l’extension du programme en bytecode ?",
			"Class Test{ Public Test () { System.out.println(”Bonjour”);} public Test (int i) { this();  System.out.println(”Nous sommes en ”+i+ ” !”);}; } qu’affichera l’instruction suivante?  Test t1=new Test (2018);",
			"Voici un constructeur de la classe Employee, y-at'il un problème ? Public void Employe(String n){ Nom=n;}",
			"Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration, on la déclare comme une constante avec le mot réservé",
			"Dans une classe, on accède à ses variables grâce au mot clé ",
			" ",
			"calculerSalaire(int)  calculerSalaire(int, double) La méthode calculerSalaire est : ",
			"Une classe qui contient au moins une méthode abstraite doit être déclarée abstraite. ",
			"Est-ce qu’une classe peut implémenter plusieurs interfaces ? ",
			"La déclaration d'une méthode suivante : public void traitement() throws IOException précise que la méthode propage une exception contrôlée ",
			"class Test{ public static void main (String[] args) { try { int a =10; System.out.println ('a = ' + a ); int b = 0 / a; System.out.println ('b = ' + b); } catch(ArithmeticException e) {System.out.println ('diviser par 0!'); } finally {System.out.println ('je suis à l’intérieur de finally';}}} ",
			" "
			};

	String[][] options = {
			{ "Compilé ", "Interprété ", "Compilé et interpreté " },
			{ "Main ", "Object ", "AWT " },
			{ "est en minuscule", "commence par une majuscule","est e n majuscule" },
			{ "oui", "non ", " " }, 
			{ "Interfacce", "Classe parent", "Package" },
			{ " " }, 
			{ "aucun des choix", "Classe .JAVA", ".Class" },
			{ "aucun des choix", "Bonjour Nous sommes en 2018 ! ", "Nous sommes en 2018 ! " },
			{ "vrai", "faux", " " },
            { "aucun des choix", "final", "const" },
			{ "aucun des choix", "this", "super" },
			{ " " },
			{ "aucun des choix", "surchargée", "redéfinie" },
			{ "vrai", "faux", " " },
			{ "vrai", "faux", " " },
			{ "vrai", "faux", " " },
			{ "aucun choix", "a=10  b=0 Je suis à l’intérieur de finally", "a=10 b=0 diviser par 0!  je suis à l’intérieur de finally" },
			{ " " }
            };

	char[] answers = { 'C', 'C', 'C', 'C', 'C', ' ', 'C', 'C', 'C', 'C', 'C', ' ','C', 'C', 'C', 'C', 'C'};

	char guess;
	char answer;
	int index;
	int correct_guesses = 0;
	int total_questions = questions.length;
	int result;
	int seconds = 300;
	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JTextArea textarea = new JTextArea();
	JTextField winLose = new JTextField();
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonLevel2 = new JButton();
	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField percentage = new JTextField();

	// timer
	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			if (seconds <= 0) {
				displayAnswer();
				
			}
		}
	});

	public Quiz() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 650);
		frame.getContentPane().setBackground(new Color(153, 50, 204));
		frame.setLayout(null);
		frame.setResizable(false);
		// nb of question
		textfield.setBounds(0, 0, 1500, 50);
		textfield.setBackground(new Color(165, 42, 42));
		textfield.setForeground(new Color(245, 245, 245));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 25));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);
		//you lose / win
		winLose.setBounds(150, 500, 250, 100);
		winLose.setBackground(new Color(25, 25, 25));
		winLose.setForeground(new Color(245, 245, 245));
		winLose.setFont(new Font("Ink Free", Font.BOLD, 25));
		winLose.setBorder(BorderFactory.createBevelBorder(1));
		winLose.setHorizontalAlignment(JTextField.CENTER);
		winLose.setEditable(false);

		// question
		textarea.setBounds(0, 50, 1500, 70);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(25, 25, 25));
		textarea.setForeground(new Color(245, 245, 245));
		textarea.setFont(new Font("Ink Free", Font.BOLD, 25));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);

		buttonA.setBounds(0, 120, 100, 100 );
		buttonA.setFont(new Font("Ink Free", Font.BOLD, 25));
		buttonA.setBackground(new Color(25, 25, 25));
		buttonA.setForeground(new Color(245, 245, 245));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");

		buttonB.setBounds(0, 220, 100, 100);
		buttonB.setFont(new Font("Ink Free", Font.BOLD, 25));
		buttonB.setBackground(new Color(25, 25, 25));
		buttonB.setForeground(new Color(245, 245, 245));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");

		buttonC.setBounds(0, 320, 100, 100);
		buttonC.setFont(new Font("Ink Free", Font.BOLD, 25));
		buttonC.setBackground(new Color(25, 25, 25));
		buttonC.setForeground(new Color(245, 245, 245));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");

		buttonLevel2.setBounds(400, 500, 200, 100);
		buttonLevel2.setFont(new Font("Ink Free", Font.BOLD, 25));
		buttonLevel2.setFocusable(false);
		buttonLevel2.addActionListener(this);
		buttonLevel2.setText("next level");

		answer_labelA.setBounds(125, 120, 500, 100);
		answer_labelA.setBackground(new Color(50, 50, 50));
		answer_labelA.setForeground(new Color(245, 245, 245));
		answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

		answer_labelB.setBounds(125, 220, 500, 100);
		answer_labelB.setBackground(new Color(50, 50, 50));
		answer_labelB.setForeground(new Color(245, 245, 245));
		answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

		answer_labelC.setBounds(125, 320, 500, 100);
		answer_labelC.setBackground(new Color(50, 50, 50));
		answer_labelC.setForeground(new Color(245, 245, 245));
		answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

		seconds_left.setBounds(800, 500, 100, 100);
		seconds_left.setBackground(new Color(25, 25, 25));
		seconds_left.setForeground(new Color(255, 0, 0));
		seconds_left.setFont(new Font("Ink Free", Font.BOLD, 25));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));

		time_label.setBounds(750, 470, 200, 25);
		time_label.setBackground(new Color(50, 50, 50));
		time_label.setForeground(new Color(255, 0, 0));
		time_label.setFont(new Font("Ink Free", Font.BOLD, 25));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("Chrono 5 min");


		percentage.setBounds(225, 325, 200, 100);
		percentage.setBackground(new Color(25, 25, 25));
		percentage.setForeground(new Color(245, 245, 245));
		percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);

		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonLevel2);
		frame.add(textfield);
		frame.add(winLose);
		frame.add(textarea);
		frame.setVisible(true);
		buttonLevel2.setVisible(false);
		nextQuestion();
	}

	public void nextQuestion() {
		
		if (index == 5 || index == 11 || index == 17) {
			percentage.setVisible(true);
			results();
		} else {	
			percentage.setVisible(false);
			textfield.setText("question" + (index + 1));
			textarea.setText(questions[index]);
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			timer.start();
		}
	}

	public void actionPerformed(ActionEvent e) {
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);

		if (e.getSource() == buttonA) {
			answer = 'A';
			if (answer == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonB) {
			answer = 'B';
			if (answer == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonC) {
			answer = 'C';
			if (answer == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonLevel2) {
			if (index==0) {
				correct_guesses=0;
			}else if(index==5){
				index = 5;
				correct_guesses=0;
			}else if(index==11){
				index = 11;
				correct_guesses=0;
			}else if(index==17){
				index = 17;
				percentage.setVisible(true);

			}else{
				percentage.setVisible(false);
			}

		}
		displayAnswer();
	}

	public void displayAnswer() {
		timer.stop();
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);

		if (answers[index] != 'A') {
			answer_labelA.setForeground(new Color(255, 0, 0));
		}
		if (answers[index] != 'B') {
			answer_labelB.setForeground(new Color(255, 0, 0));
		}
		if (answers[index] != 'C') {
			answer_labelC.setForeground(new Color(255, 0, 0));
		}

		Timer pause = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer_labelA.setForeground(new Color(25, 255, 0));
				answer_labelB.setForeground(new Color(25, 255, 0));
				answer_labelC.setForeground(new Color(25, 255, 0));
				answer = ' ';
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}

	public void results() {
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);

		result = (int) (correct_guesses * 20);
		if (index == 5 && result < 40) {
			percentage.setVisible(true);
			buttonLevel2.setVisible(false);
			winLose.setText("You lose in level 1");

		}else if(index == 5 && result >= 40){
			percentage.setVisible(true);
			buttonLevel2.setVisible(true);
			winLose.setText("You Win in level 1");
		}else if(index == 11 && result >= 60){
			percentage.setVisible(true);
			buttonLevel2.setVisible(true);
			winLose.setText("You Win in level 2");
		}else if(index == 11 && result < 60){
			percentage.setVisible(true);
			buttonLevel2.setVisible(false);
			winLose.setText("You LOSE in level 2");
		}else if (index > 11){
			buttonLevel2.setVisible(false);

		}else if(index == 17 && result >= 80){
			percentage.setVisible(true);
			buttonLevel2.setVisible(false);
			winLose.setText("You Win in level 3");
		}else if(index == 17 && result < 80){
			percentage.setVisible(true);
			buttonLevel2.setVisible(false);
			winLose.setText("You LOSE in level 3");
		}
		textfield.setText("RESULTS!");
		textarea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");

		percentage.setText(result + "%");
		frame.add(percentage);
	}

}
