import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class HangmanJava{
public static class HangmanGUI extends JFrame {
public static final String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
public static String word = words[(int) (Math.random() * words.length)];
public static String hiddenWord = new String(new char[word.length()]).replace('\0', '-');
public static int guessesLeft = 6;
public JPanel mainPanel;
public JLabel wordLabel;
public JLabel hangmanLabel;
public JLabel guessesLabel;
public JTextField inputField;
public JButton guessButton;
public ImageIcon[] hangmanImages = {
new ImageIcon("D:\\Desktop\\Hangman\\Hangman-0.png"),
new ImageIcon("D:\\Desktop\\Hangman\\Hangman-1.png"),
new ImageIcon("D:\\Desktop\\Hangman\\Hangman-2.png"),
new ImageIcon("D:\\Desktop\\Hangman\\Hangman-3.png"),
new ImageIcon("D:\\Desktop\\Hangman\\Hangman-4.png"),
new ImageIcon("D:\\Desktop\\Hangman\\Hangman-5.png"),
new ImageIcon("D:\\Desktop\\Hangman\\Hangman-6.png")
};
public HangmanGUI() {
mainPanel = new JPanel();
mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
wordLabel = new JLabel(hiddenWord);
wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
wordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
mainPanel.add(wordLabel);
hangmanLabel = new JLabel(hangmanImages[0]);
hangmanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
mainPanel.add(hangmanLabel);
guessesLabel = new JLabel("Guesses left: " + guessesLeft);
guessesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
mainPanel.add(guessesLabel);
inputField = new JTextField(10);
inputField.setMaximumSize(new Dimension(200, 30));
inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
mainPanel.add(inputField);
guessButton = new JButton("Guess");
guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
guessButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
String guess = inputField.getText();
if (guess.length() == 1) {
processGuess(guess.charAt(0));
}
inputField.setText("");
}
});
mainPanel.add(guessButton);
add(mainPanel);
setTitle("Hangman");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pack();
setLocationRelativeTo(null);
setVisible(true);
}
public void processGuess(char guess) {
if (word.indexOf(guess) != -1) {
for (int i = 0; i < word.length(); i++) {
if (word.charAt(i) == guess) {
hiddenWord = hiddenWord.substring(0, i) + guess + hiddenWord.substring(i + 1);
}
}
wordLabel.setText(hiddenWord);
if (!hiddenWord.contains("-")) {
endGame(true);
}
} else {
guessesLeft--;
guessesLabel.setText("Guesses left: " + guessesLeft);
hangmanLabel.setIcon(hangmanImages[6 - guessesLeft]);
if (guessesLeft == 0) {
endGame(false);
}
}
}
public void endGame(boolean win) {
guessButton.setEnabled(false);
inputField.setEnabled(false);
String message = win ? "You win! The word was: " + word : "You lose! The word was: " + word;
JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
}
public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
new HangmanGUI();
}
}
);}}}
