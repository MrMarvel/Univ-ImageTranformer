import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Класс интерфейса страницы приложения.
 * @author Панкрухин Максим.
 * @version 1.0.0.
 */
public class App extends JPanel {

    ImageIcon picture = new ImageIcon(new ImageIcon("src/java/StandartImage.png").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT));
    JLabel img;
    AddFile FileOp;
    JComboBox comboBox;

    /**
     * Конструктор класса App.
     */
    App(){
        setSize(1080,720);
        setBackground(new Color(191, 80, 60));
        //Добавление картинки по-умолчанию
        img = new JLabel(picture);
        img.setBackground(Color.gray);
        img.setBounds(50, 50, 600, 400);
        add(img);
        JLabel addFormat = new JLabel("Выберите расширение файла");
        addFormat.setBounds(670, 50, 300, 30);
        customFontColor fontsLib = new customFontColor();
        addFormat.setFont(fontsLib.FiraSans_Bold);
        add(addFormat);

        //Добавление функционала выбора файла
        FileOp = new AddFile(img, picture);
        add(FileOp);
        //Добавление выпадающего списка расширений
        String[] components = {"png", "jpg", "gif"};
        comboBox = new JComboBox(components);
        comboBox.setBounds(670, 80, 200, 30);
        comboBox.setBackground(new Color(231, 120, 100));
        add(comboBox);
        //Добавление функционала кнопки старта
        add(new SaveFiles(FileOp, comboBox));

        //Добавление градиента
        add(new pan());

        setLayout(null);
        setVisible(true);
    }
}

/**
 * Класс создания дизайна градиента страницы.
 * @author Панкрухин Максим.
 * @version 1.0.0.
*/
class pan extends JPanel{
    /**
     * Конструктор класса Pan.
     */
    pan(){
        setSize(1080, 220);
        setLocation(0, 500);
        setVisible(true);
    }

    /**
     * Переопределенный метод рисования градиента страницы.
     * @param g объект класса Graphics.
     * @see Graphics
     */
    public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(191, 80, 60),
                    0, 220, new Color(161, 50, 30));
            g2.setPaint(gradientPaint);
            g2.fill(new Rectangle2D.Double(0, 0, 1080, 220));
    }
}

/**
 * Класс стартовой кнопки и сохранения файла.
 * @author Панкрухин Максим, Булдаков Никита.
 * @version 1.0.0.
*/
class SaveFiles extends JButton implements ActionListener{
    AddFile FileOp;
    JComboBox comboBox;

    /**
     * Конструктор класса SaveFiles.
     * @param FileOp объект класса File выбранного файла.
     * @see File – класс файла.
     * @param comboBox объект компонента класса JComboBox выбранного расширения нового файла.
     * @see JComboBox класс выпадающего списка.
     */
    SaveFiles(AddFile FileOp, JComboBox comboBox){
        this.FileOp = FileOp; this.comboBox = comboBox;
        setBounds(210, 470, 40, 40);
        setOpaque(true);
        setBackground(new Color(166, 30, 30));
        setFocusPainted(false);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        setIcon(new ImageIcon(new ImageIcon("src/java/Start.png").getImage().getScaledInstance(20, 25, Image.SCALE_DEFAULT)));

        addActionListener(this);
    }

    /**
     * Метод получения пути выбранного файла.
     * @return возвращает значение пути выбранного изменяемого файла.
     */
    public String getFilePath() {
        return FileOp.Select();
    }

    /**
     * Метод получения выбранного расширения.
     * @return возвращает значение выбранного расширения нового файла.
     */
    public String getComboBox() {
        return (String)comboBox.getSelectedItem();
    }

    /**
     * Метод обработки нажатия кнопки старта.
     * @see SaveFiles#SaveFiles(AddFile, JComboBox)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Вычленяем путь и новый формат (для удобства)
        String path = getFilePath();
        String format = getComboBox();
        //Сохраняем путь до нового файла в переменной newFileName

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(SaveFiles.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
        }

        String newFileName = Converter.convert(path, format, String.valueOf(fileChooser.getSelectedFile()));
        //Делай с ним все, что хочешь)
        System.out.println(newFileName);
        //Телеграмма из центра дошла до Штирлица не сразу. Пришлось перечитывать.

    }
}

/**
 * Класс выбора файла из каталога.
 * @author Панкрухин Максим.
 * @version 1.0.0.
 */
class AddFile extends JButton implements ActionListener{

    JFileChooser fc;
    File SelFile;
    ImageIcon picture;
    JLabel img;
    String[] components = {"png", "jpg", "gif"};

    /**
     * Конструктор класса AddFile.
     * @param img объект класса JLabel. Используется для предпросмотра выбранного изображения или изображения по умолчанию.
     * @see JLabel
     * @param picture объект класса ImageIcon. Содержит картинку по умолчанию.
     * @see ImageIcon
     */
    AddFile(JLabel img, ImageIcon picture){

        this.img = img;
        this.picture = picture;
        //Создаем объект класса библиотеки пользовательских шрифтов
        customFontColor fontsLib = new customFontColor();

        setSize(150, 40);
        setLocation(50, 470);
        setText("Выберите файл");

        setFont(fontsLib.FiraSans_Bold);
        setForeground(new Color(240, 240, 240));
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(new Color(166, 30, 30));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);

        fc = new JFileChooser();
        setFocusPainted(false);
        addActionListener(this);
    }

    /**
     * Метод обработки пути выбранного файла.
     * @return возвращает путь выбранного файла или сообщение об ошибке.
     */

    String Select(){
        SelFile = fc.getSelectedFile();
        boolean state = true; StringBuilder format = new StringBuilder();
        String Path = SelFile.getPath();

        //Проверка формата файла
        //Срез строки пути файла до расширения
        for (char ch : Path.toCharArray()) {
            if (ch != '.' && state) {
                continue;
            }
            else {
                state = false;
                if (ch != '.') format.append(ch);
            }
        }

/*        if (format.toString().equals("pdf") || format.toString().equals("psd")){
            BufferedImage bimg = null;
            try {
                bimg = ImageIO.read(new File("src/java/doc.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Integer[] picSize = sizeImg(bimg.getWidth(), bimg.getHeight());
            ImageIcon pic = new ImageIcon(new ImageIcon("src/java/doc.png").getImage().getScaledInstance(picSize[0], picSize[1], Image.SCALE_DEFAULT));
            img.setIcon(pic);
            return SelFile.getPath();
        }else {
  */
        //Сверка среза с шаблонами форматов
            for (String str : components) {
                if (str.equals(format.toString()) && !format.toString().equals("")) {
                    state = true;
                    break;
                }
            } //    }
        //Результат сверки
        if (state) {
            BufferedImage bimg = null;
            try {
                bimg = ImageIO.read(new File(SelFile.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Integer[] picSize = sizeImg(bimg.getWidth(), bimg.getHeight());
            ImageIcon pic = new ImageIcon(new ImageIcon(SelFile.getPath()).getImage().getScaledInstance(picSize[0], picSize[1], Image.SCALE_DEFAULT));
            img.setIcon(pic);
            img.setLocation(50, 50);
            return SelFile.getPath();
        }
        else {
            JOptionPane.showMessageDialog(this,"Invalid format of selected file. Choose it again.","Error: Invalid format", JOptionPane.ERROR_MESSAGE);
            img.setIcon(picture);
            return "Error: Invalid format of selected file.";
        }
    }

    /**
     * Метод подбора значений размера изображения на предпросмотре.
     * @param x размер выбранного изображения по оси X.
     * @param y размер выбранного изображения по оси Y.
     * @return возвращает массив чисел целочисленного типа, содержащий измененный размер изображения по осям X и Y соответственно.
     */
    Integer[] sizeImg(int x, int y){
        Integer[] size = new Integer[2];
        double prop, y1=y, x1=x;
        if (x>y){
            prop = (double) y/(double)x;
            while (x1 < 600) x1++;
            while (x1 > 600) x1--;
            y1 = (int) (x1*prop);
        } else {
            prop = (double) x/(double)y;
            while (y1 < 400) y1++;
            while (y1 > 400) y1--;
            x1 = (int) (y1*prop);
        }

        size[0] = (int)x1;size[1] = (int)y1;
        return size;
    }

    /**
     * Метод отображения выбора файла из файловой локальной системы.
     * @param e объект класса ActionEvent.
     * @see ActionListener#actionPerformed(ActionEvent)
     * @see ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fc.showOpenDialog(AddFile.this);
        try {
            Select();
        } catch (NullPointerException Exception){
            System.err.println("Не выбран файл");
        }

    }
}

/**
 * Класс хранения и подключения пользовательских шрифтов.
 * @author Панкрухин Максим.
 * @version 1.0.0.
 */
class customFontColor{
    Font FiraSans_Black, FiraSans_BlackItalic,
            FiraSans_Bold, FiraSans_BoldItalic,
            FiraSans_ExtraBold, FiraSans_ExtraBoldItalic, FiraSans_ExtraLight, FiraSans_ExtraLightItalic,
            FiraSans_Italic, FiraSans_Light, FiraSans_LightItalic,
            FiraSans_Medium, FiraSans_MediumItalic,
            FiraSans_Regular, FiraSans_SemiBold, FiraSans_SemiBoldItalic,
            FiraSans_Thin, FiraSans_ThinItalic;
    customFontColor(){
        try {
            FiraSans_Bold = Font.createFont(Font.TRUETYPE_FONT, new File("src/java/Fonts/FiraSans-Bold.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(FiraSans_Bold);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Класс конвертации изображения в другое расширение и сохранение нового файла по выбранному пути в локальной файловой системе.
 * @author Булдаков Никита, Панкрухин Максим.
 * @version 1.1.0.
 */
class Converter{
    /**
     * Метод конвертации и сохранения изображения.
     * @param path путь выбранного изменяемого файла.
     * @param format выбранное расширение нового файла изображения.
     * @param newPath путь сохраняемого изображения.
     * @return возвращает путь сохраненного изображения.
     */
    public static String convert(String path, String format, String newPath) {
        BufferedImage bufferedImage;
        try {
            //Считываем изображение в буфер
            bufferedImage = ImageIO.read(new File(path));

            // создаем пустое изображение RGB, с тай же шириной высотой и белым фоном
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

            // записываем новое изображение в формате jpg
            ImageIO.write(newBufferedImage, format, new File(newPath));

            return newPath;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}