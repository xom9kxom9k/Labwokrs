import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PhoneFrame {
    public JPanel mainPanel;
    private JList<Object> list;
    private DefaultListModel<Object> defaultListModel;
    private PhoneList listPhone;

    public PhoneFrame() {
        listPhone = new PhoneList();
        addFrameElements();
    }

    private void addFrameElements() {
        mainPanel = new JPanel();
        JPanel leftBlock = new JPanel();
        JPanel rightBlock = new JPanel();

        JTextField nameField = new JTextField();
        JTextField versionField = new JTextField();
        JTextField yearReleasedField = new JTextField();

        JButton buttonAdd = new JButton("Добавить");
        JButton buttonDel = new JButton("Удалить");
        JButton buttonRemake = new JButton("Изменить");
        JButton buttonSearch = new JButton("Поиск");

        GridLayout iMoreLikeFlexbox = new GridLayout(0, 1);

        leftBlock.setLayout(iMoreLikeFlexbox);
        rightBlock.setLayout(iMoreLikeFlexbox);
        mainPanel.setLayout(new GridLayout(1, 2));

        JLabel name = new JLabel("Название смартфона:", SwingConstants.CENTER);
        JLabel version = new JLabel("Версия смартфона:", SwingConstants.CENTER);
        JLabel yearReleased = new JLabel("Год выпуска:", SwingConstants.CENTER);
        mainPanel.add(leftBlock);
        mainPanel.add(rightBlock);
        leftBlock.add(name);
        leftBlock.add(nameField);
        leftBlock.add(version);
        leftBlock.add(versionField);
        leftBlock.add(yearReleased);
        leftBlock.add(yearReleasedField);
        leftBlock.add(buttonAdd);
        leftBlock.add(buttonDel);
        leftBlock.add(buttonRemake);
        leftBlock.add(buttonSearch);


        defaultListModel = new DefaultListModel<>();
        list = new JList<>(defaultListModel);
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(list);
        rightBlock.add(pane);

        JButton buttonFilter = new JButton("Фильтр");
        JButton buttonClearFilter = new JButton("Очистить фильтр");

        rightBlock.add(buttonFilter);
        rightBlock.add(buttonClearFilter);
        leftBlock.setOpaque(true);
        leftBlock.setBackground(new Color(128, 128, 128));
        Object[] a = listPhone.to_String();
        for (int i = 0; i < a.length; i++) {
            defaultListModel.addElement(a[i]);
        }
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listPhone.ListData.add(new Phone(nameField.getText(), Integer.parseInt(versionField.getText()), Integer.parseInt(yearReleasedField.getText())));
                    defaultListModel.clear();
                    Object[] a = listPhone.to_String();
                    for (int i = 0; i < a.length; i++) {
                        defaultListModel.addElement(a[i]);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Неправильные данные");
                }
            }
        });
        buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Данные не выбраны");
                } else {
                    listPhone.ListData.remove(list.getSelectedValue());
                    defaultListModel.clear();
                    Object[] a = listPhone.to_String();
                    for (int i = 0; i < a.length; i++) {
                        defaultListModel.addElement(a[i]);
                    }
                }
            }
        });

        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultListModel.clear();
                List<Phone> tmp = listPhone.streamFilter();
                for (Phone i : tmp) {
                    defaultListModel.addElement(i);
                }
            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("Поиск");
                frame.setDefaultLookAndFeelDecorated(true);
                frame.setVisible(true);
                JPanel panel = new JPanel();
                frame.pack();
                frame.setContentPane(panel);
                frame.setSize(500, 300);

                frame.setLocation(200, 200);


                JTextField nameField = new JTextField();
                JTextField versionField = new JTextField();
                JTextField yearReleasedField = new JTextField();

                JLabel about = new JLabel("Что искать?", SwingConstants.CENTER);
                JLabel name = new JLabel("Название смартфона:", SwingConstants.CENTER);
                JLabel version = new JLabel("Версия смартфона:", SwingConstants.CENTER);
                JLabel yearReleased = new JLabel("Год выпуска", SwingConstants.CENTER);
                panel.setLayout(new GridLayout(0, 1));
                panel.add(about);
                panel.add(name);
                panel.add(nameField);
                panel.add(version);
                panel.add(versionField);
                panel.add(yearReleased);
                panel.add(yearReleasedField);
                JButton button = new JButton("Искать");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            defaultListModel.clear();

                            String nameInput = nameField.getText();
                            int versionInput = Integer.parseInt(versionField.getText());
                            int yearReleasedInput = Integer.parseInt(yearReleasedField.getText());

                            List<Phone> tmp = listPhone.streamSearch(versionInput,yearReleasedInput,nameInput);
                            for (Phone i : tmp) {
                                defaultListModel.addElement(i);
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Неправильные данные ");
                        } finally {
                            frame.dispose();
                        }
                    }
                });
                panel.add(button);
            }
        });
        buttonClearFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultListModel.clear();
                Object[] a = listPhone.to_String();
                for (int i = 0; i < a.length; i++) {
                    defaultListModel.addElement(a[i]);
                }
            }
        });
        buttonRemake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (list.getSelectedValue() != null) {
                    JFrame frame = new JFrame("Изменение параметров");
                    frame.setDefaultLookAndFeelDecorated(true);
                    frame.setVisible(true);
                    JPanel panel = new JPanel();
                    frame.pack();
                    frame.setContentPane(panel);
                    frame.setSize(500, 300);
                    frame.setLocation(200, 200);

                    JTextField nameField = new JTextField();
                    JTextField versionField = new JTextField();
                    JTextField yearReleasedField = new JTextField();
                    JTextField wasLike = new JTextField(list.getSelectedValue().toString());

                    JLabel about = new JLabel("Изменить параметры", SwingConstants.CENTER);
                    JLabel was = new JLabel("Изначальные данные:", SwingConstants.CENTER);
                    JLabel name = new JLabel("Название смартфона:", SwingConstants.CENTER);
                    JLabel bitDepth = new JLabel("Версия смартфона:", SwingConstants.CENTER);
                    JLabel yearReleased = new JLabel("Год выпуска:", SwingConstants.CENTER);
                    panel.setLayout(new GridLayout(0, 1));
                    panel.add(about);
                    panel.add(was);
                    panel.add(wasLike);
                    panel.add(name);
                    panel.add(nameField);
                    panel.add(bitDepth);
                    panel.add(versionField);
                    panel.add(yearReleased);
                    panel.add(yearReleasedField);
                    JButton button = new JButton("Изменить данные");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            try {

                                listPhone.ListData.add(listPhone.ListData.indexOf(list.getSelectedValue()), new Phone(nameField.getText(), Integer.parseInt(versionField.getText()), Integer.parseInt(yearReleasedField.getText())));
                                listPhone.ListData.remove(list.getSelectedValue());
                                defaultListModel.clear();
                                Object[] a = listPhone.to_String();
                                for (int i = 0; i < a.length; i++) {
                                    defaultListModel.addElement(a[i]);
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Неправильные данные ");
                            } finally {
                                frame.dispose();
                            }
                        }
                    });
                    panel.add(button);
                } else {
                    JOptionPane.showMessageDialog(null, "Не выбрано");
                }
            }
        });
    }
}
