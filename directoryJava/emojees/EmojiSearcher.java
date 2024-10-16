import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmojiSearcher extends JFrame implements KeyListener {

    private final List<Emoji> emojiList;  // Replace with your actual emoji data
    private final JTextField searchField;
    private final JPanel emojiContainer;

    public EmojiSearcher() {
        super("Emoji Searcher");
        emojiList = new ArrayList<>(); // Populate with emoji data
        searchField = new JTextField();
        emojiContainer = new JPanel(new FlowLayout());

        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.addKeyListener(this);

        add(searchField, BorderLayout.NORTH);
        add(emojiContainer, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void displayEmoji(String searchQuery) {
        List<Emoji> filteredList = emojiList.stream()
                .filter(emoji -> searchQuery.isEmpty() || emoji.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        emojiContainer.removeAll();
        for (Emoji emoji : filteredList) {
            JLabel emojiLabel = new JLabel(emoji.getEmoji());
            emojiLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
            emojiContainer.add(emojiLabel);
        }
        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        displayEmoji(searchField.getText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmojiSearcher::new);
    }
}