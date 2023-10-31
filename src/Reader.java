import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Reader extends JFrame {
    public static int bookCounter = 0;
    int counter;
    Object objectBoxDay, objectBoxMonth, objectBoxYear;
    File dataFile = new File(
            "D://Универ//3 курс 1 семестр//ПрогСП//5 лаба//lab5_part2//src//DataFile.txt");
    static JLabel nameLabel, authorLabel, descriptionLabel, publisherLabel;
    static JLabel dateLabel, eVersionLabel;
    static JComboBox boxDay, boxMonth, box_3;
    static JRadioButton eVersionIs, eVersionNot;
    static ButtonGroup buttonGroup;
    static JButton button, remove;
    static JTextField bookTitle, author, publisher;
    static JTextArea bookDescription;
    static String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
            "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    static String[] month = {"январь","февраль","март","апрель","май","июнь","июль","август",
            "сентябрь","октябрь","ноябрь","декабрь"};
    static String[] year = {"1999","2000","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011",
            "2012","2013","2014","2015","2016","2017","2018","2019","2020", "2021", "2022", "2023"};

    public Reader(String str){
        super(str);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(205, 133, 63));

        button = new JButton("Записать в файл");
        remove = new JButton("Очистить");
        bookTitle = new JTextField(9);
        author = new JTextField(9);
        publisher = new JTextField(9);
        bookDescription = new JTextArea(9, 9);
        nameLabel = new JLabel("Название книги");
        authorLabel = new JLabel("Автор");
        descriptionLabel = new JLabel("Краткое описание");
        publisherLabel = new JLabel("Издательство");
        dateLabel = new JLabel("Дата издания");
        eVersionLabel = new JLabel("Наличие электронной версии");
        boxDay = new JComboBox(day);
        boxMonth = new JComboBox(month);
        box_3 = new JComboBox<Object>(year);
        eVersionIs = new JRadioButton("есть");
        eVersionNot = new JRadioButton("нет");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(eVersionIs);
        buttonGroup.add(eVersionNot);
        setLayout(null);
        nameLabel.setForeground(Color.white);
        authorLabel.setForeground(Color.white);
        descriptionLabel.setForeground(Color.white);
        publisherLabel.setForeground(Color.white);
        dateLabel.setForeground(Color.white);
        eVersionLabel.setForeground(Color.white);
        remove.setBackground(new Color(245, 222, 179));
        button.setBackground(new Color(245, 222, 179));

        button.setSize(200,30);
        button.setLocation(220, 400);
        remove.setSize(100, 30);
        remove.setLocation(80, 400);
        bookTitle.setSize(220,25);bookTitle.setLocation(250, 20);
        author.setSize(220,25);author.setLocation(250, 60);
        bookDescription.setSize(220,70);
        bookDescription.setLocation(250,100);
        publisher.setSize(220,25);publisher.setLocation(250, 200);
        nameLabel.setSize(200,25);
        nameLabel.setLocation(30, 20);
        authorLabel.setSize(220, 25);
        authorLabel.setLocation(30, 60);
        descriptionLabel.setSize(200, 25);
        descriptionLabel.setLocation(30, 100);
        publisherLabel.setSize(220, 25);
        publisherLabel.setLocation(30, 200);
        dateLabel.setSize(200, 25);dateLabel.setLocation(30, 250);
        eVersionLabel.setSize(200, 25);
        eVersionLabel.setLocation(30, 300);
        boxDay.setSize(40,25);
        boxDay.setLocation(250,250);
        boxMonth.setSize(100, 25);
        boxMonth.setLocation(300, 250);
        box_3.setSize(70, 25);box_3.setLocation(410, 250);
        eVersionIs.setSize(70,25);
        eVersionIs.setLocation(250, 310);
        eVersionNot.setSize(50, 25);
        eVersionNot.setLocation(320, 310);

        add(button);
        add(remove);
        add(bookTitle);
        add(author);
        add(publisher);
        add(bookDescription);
        add(nameLabel);
        add(authorLabel);
        add(descriptionLabel);
        add(publisherLabel);
        add(dateLabel);
        add(eVersionLabel);
        add(boxDay);
        add(boxMonth);
        add(box_3);
        add(eVersionIs);
        add(eVersionNot);

        button.addActionListener(new ButtonActionListener());
        eVersionIs.addActionListener(new FlagActionListener());
        eVersionNot.addActionListener(new FlagActionListener());
        remove.addActionListener(new DelActionListener());
        boxDay.addActionListener(new BoxActionListener());
        boxMonth.addActionListener(new BoxActionListener());
        box_3.addActionListener(new BoxActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try{
                if(!dataFile.exists()){
                    dataFile.createNewFile();
                }
                FileWriter out = new FileWriter(dataFile, true);
                try{
                    bookCounter++;
                    String text1 = bookTitle.getText();
                    String text2 = author.getText();
                    String area2 = bookDescription.getText();
                    String text3 = publisher.getText();
                    out.write("\nНомер книги:"+ bookCounter + "\n");
                    out.write(text1 + " - "+ text2 + "\n");
                    out.write("Краткое описание: " + area2 + "\n");
                    out.write("Издательство: " + text3 + "\n");
                    if(counter == 1)
                        out.write("Электронный формат присутствует. " + "\n");
                    else if(counter == -1)
                        out.write("Электронный формат отсутствует. " + "\n");
                    out.write("Дата издания:" + objectBoxDay + " " + objectBoxMonth + " " + objectBoxYear + "\n");
                } finally{
                    out.close();
                }
            }catch(IOException e1){
                throw new RuntimeException(e1);
            }
        }
    }

    public class FlagActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            counter = 0;
            if(e.getSource() == eVersionIs){
                counter++;
            }
            else if(e.getSource() == eVersionNot){
                counter--;
            }
        }
    }
    public class DelActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == remove){
                bookTitle.setText(null);
                author.setText(null);
                bookDescription.setText(null);
                publisher.setText(null);
            }
        }
    }
    public class BoxActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == boxDay){
                objectBoxDay = boxDay.getSelectedItem();
            }
            if(e.getSource() == boxMonth){
                objectBoxMonth = boxMonth.getSelectedItem();
            }
            if(e.getSource() == box_3){
                objectBoxYear = box_3.getSelectedItem();

            }
        }
    }
}
