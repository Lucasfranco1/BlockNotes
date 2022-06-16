import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Window extends JFrame {

    private JMenuBar menuBar;
    private JMenu file, help;
    private JMenuItem news, open, save, close, about;
    private JTextArea textArea;

    //Scroll in components
    private JScrollPane jScrollPane;

    public Window(){
        super("SuperBlock");

        Font font= new Font("Console", Font.PLAIN, 18);
        //Menu Bar
        menuBar= new JMenuBar();
        file= new JMenu("File");
        Color colorFMenuOption= new Color(165, 211, 255);
        file.setFont(font);
        file.setForeground(colorFMenuOption);
        help= new JMenu("Help");
        help.setForeground(colorFMenuOption);
        help.setFont(font);


        Color colorMenuBar= new Color(19, 26, 53);
        UIManager.put("MenuBar.background", colorMenuBar);
        UIManager.put("MenuBar.opaque", true);
        //Menu Item
        UIManager.put("MenuItem.background", colorMenuBar);
        UIManager.put("MenuItem.opaque", true);


        news= new JMenuItem("New");
        news.setForeground(colorFMenuOption);
        news.setFont(font);
        news.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //eliminate text Area
                textArea.setText("");
            }
        });
        open=new JMenuItem("Open");
        open.setForeground(colorFMenuOption);
        open.setFont(font);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openFile();
            }
        });
        save= new JMenuItem("Save");
        save.setForeground(colorFMenuOption);
        save.setFont(font);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveFile();
            }
        });
        close= new JMenuItem("Close");
        close.setForeground(Color.RED);
        close.setFont(font);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                System.exit(0);
            }
        });
        about= new JMenuItem("About");
        about.setForeground(colorFMenuOption);
        about.setFont(font);
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Super Block made in Java Swing, by Lucas Franco.\n ");
            }
        });

        //Menu Text Area
        textArea= new JTextArea();
        Font fontArea= new Font("Verdana", Font.PLAIN, 20);
        textArea.setFont(fontArea);
        textArea.setForeground(colorMenuBar);

        Color colorBackgroundTextArea= new Color(233, 249, 255);
        textArea.setBackground(colorBackgroundTextArea);
        jScrollPane= new JScrollPane(textArea);
        //create menu
        menuBar.add(file);
        menuBar.add(help);

        file.add(news);
        file.add(open);
        file.add(save);

        //line separator
        file.addSeparator();
        file.add(close);

        help.add(about);


        setLayout(new BorderLayout());
        add(jScrollPane, BorderLayout.CENTER);

        setJMenuBar(menuBar);

    }

    private void saveFile() {
        System.out.println("Save File");
        //save file
        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(fileChooser.showSaveDialog(this)== JFileChooser.APPROVE_OPTION){
            //identification the file select
            File file= fileChooser.getSelectedFile();

            FileWriter writer= null;
            try {
                writer= new FileWriter(file);

                //writer in textArea get
                writer.write(textArea.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void openFile() {
        System.out.println("Open File!");
        // open for select one file
        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //if JFileChooser APPROVE_OPTION the user chose.
        if(fileChooser.showOpenDialog(this)== JFileChooser.APPROVE_OPTION){
            //get absolute path
            String path= fileChooser.getSelectedFile().getAbsolutePath();

            try {
                //iterate y add line in text area
                List<String>lines = Files.readAllLines(Paths.get(path));
                for(String line: lines){
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
