package Database;

import javax.swing.SwingUtilities;

import view.ItemView;

public class ItemTest {
     public static void main(String[] args) {
        SwingUtilities.invokeLater(ItemView::new);
    }
}
