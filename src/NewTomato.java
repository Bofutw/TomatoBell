
//系統變量 JJ2D_D3D:false

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import tw.brad.myjava.TomatoClock;
///import tw.brad.myjava.TomatoClock.CoffeeTaskcos;

public class NewTomato extends JFrame {
    private BufferedImage imgicon, imgicon1;
    public JLabel abc2;
    int co, wo, re;
    TomatoClock abc;
    HashMap<String, int[]> HM = new HashMap();
    JComboBox<String> cbo = new JComboBox<String>();
    FileOutputStream fout;
    ObjectOutputStream oout;
    FileInputStream fin;
    ObjectInputStream oin;
    Object obj;

    NewTomato() {
        //super("番茄來囉");
        JFrame frm = new JFrame();

        setTitle("番茄來囉");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(750, 350, 400, 405); //(750, 350, 400, 400);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(new Color(250, 230, 211));


        //icon
        try {
            imgicon = ImageIO.read(new File("C:\\Users\\阿甫\\Downloads\\tomato.png"));

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        setIconImage(imgicon);

        //JP1 按鈕區
        JPanel pan1 = new JPanel();
        add(pan1);
        pan1.setBounds(30, 10, 95, 100);
        //pan1.setBorder(BorderFactory.createLineBorder(Color.black));
        pan1.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan1.setBackground(getForeground());

        JButton b1 = new JButton("經典番茄");
        JButton b2 = new JButton("自種番茄");
        JButton b3 = new JButton("重置番茄");

        pan1.add(b1);
        pan1.add(b2);
        pan1.add(b3);
        b3.setEnabled(false);

        //JP4 自訂區
        JPanel pan4 = new JPanel();

        pan4.setBounds(30, 110, 95, 250);  //265,120
        pan4.setBorder(BorderFactory.createLineBorder(Color.black));
        pan4.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan4.setBackground(getForeground());
        JLabel count = new JLabel("番茄次數：");
        JLabel worktime = new JLabel("工作時間(分)：");
        JLabel resttime = new JLabel("休息時間(分)：");
        JTextField count_in = new JTextField();
        JTextField worktime_in = new JTextField();
        JTextField resttime_in = new JTextField();
        JButton b4 = new JButton("開始番茄");
        JButton b5 = new JButton("儲存番茄");
        JButton b6 = new JButton("讀取番茄");


        count_in.setToolTipText("建議輸入1~20");
        count_in.setColumns(5);
        worktime_in.setToolTipText("建議輸入25~45");
        worktime_in.setColumns(5);
        resttime_in.setToolTipText("建議輸入5~15");
        resttime_in.setColumns(5);

        //count_in.setBounds(275,215,95,120);

        add(pan4);
        pan4.add(count);
        pan4.add(count_in);
        pan4.add(worktime);
        pan4.add(worktime_in);
        pan4.add(resttime);
        pan4.add(resttime_in);
        pan4.add(b4);

        pan4.add(b5);
        pan4.add(b6);
        pan4.setVisible(false);

        //JP2 顯示時間區、新增底圖
        JPanel pan2 = new JPanel() {
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(imgicon, 0, 0, null);
            }
        };
        add(pan2);
        pan2.setBounds(155, 15, 200, 300);  // 30 15
        pan2.setBackground(Color.PINK);
        //pan2.setBorder(BorderFactory.createLineBorder(Color.black));
        pan2.setLayout(new FlowLayout(FlowLayout.CENTER));
        //JLabel JL = new JLabel();
        //JL.setText("25:00");
        //pan2.add(JL);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getFullScreenWindow(); //將Dialog顯示在最上面
        //setFocusable(true);
        //JLabel JL_clock = new TomatoClock();
        //pan2.add(JL_clock);


        //HM = (HashMap<String, int[]>)obj;
        //測試區 In、obj宣告並指定；HM指定obj；宣告key(set)指定HM.keyset；cbo填入item；關閉In
        File tomatoDir = new File("C:/tomato");
        if (tomatoDir.exists()) {

        } else {
            tomatoDir.mkdir();
        }

        try {

            FileInputStream fin = new FileInputStream("C:/tomato/setting.tomato");
            ObjectInputStream oin = new ObjectInputStream(fin);
            Object obj = oin.readObject();


            if (obj instanceof HashMap) {
                HM = (HashMap<String, int[]>) obj;
                Set<String> key = HM.keySet();
                for (String z : key) {
                    cbo.addItem(z);
                }
            } else {
                System.out.println("if出問題");
            }
            oin.close();

        } catch (Exception e3) {
            System.out.println(e3.toString());
        }

        JButton b7 = new JButton("刪除設定檔");
        JButton b8 = new JButton("刪除");

        //測試區	結束


        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abc = new TomatoClock();
                abc.setFont(new Font("Verdana", Font.PLAIN, 50));
                pan2.add(abc);

                b1.setEnabled(false);
                b3.setEnabled(true);
                b4.setEnabled(false);

                //b1.setVisible(true);
                //音樂
//					try {
//						File file = new File("D:\\bell.wav");
//						FileInputStream infile = new FileInputStream(file);
//						AudioInputStream inAudio = new AudioInputStream(infile, null, ABORT);
//						//AudioFormat format = inAudio.getFormat();
//						Clip clip = AudioSystem.getClip();
//						clip.open(inAudio);
//						clip.setFramePosition(0);
//						clip.start();
//					} catch (Exception e1) {
//						System.out.println(e1.toString());
//					}
            }
        });


        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //if(count_in && &&)
                try {
                    int co = Integer.parseInt(count_in.getText());
                    int wo = Integer.parseInt(worktime_in.getText());
                    int re = Integer.parseInt(resttime_in.getText());


                    //
                    //int[] set_int = {co,wo,re};
                    //HashMap<String, int[]> HM = new HashMap();
                    //HM.put(,set_int);

                    //
                    if (co <= 0 || wo <= 0 || re <= 0 || co > 100 || wo > 60 || re > 60) {
                        JOptionPane.showMessageDialog(null, "請輸入正確番茄規格", "番茄難產中", JOptionPane.ERROR_MESSAGE);
                    } else {
                        abc2 = new TomatoClock(co, wo, re);
                        abc2.setFont(new Font("Verdana", Font.PLAIN, 50));
                        pan2.add(abc2);

                        b3.setEnabled(true);
                        b1.setEnabled(false);
                        b4.setEnabled(false);
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "請輸入正確番茄規格(整數)", "番茄難產中", JOptionPane.ERROR_MESSAGE);
                }
                ;
            }
        });


        // try
        // 先使用 HM放入key跟value 然後使用 cbo加入item
        // 然後呼叫Out 宣告fout oout 然後將HM寫入 最後flush跟close
        // catch
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int co2 = Integer.parseInt(count_in.getText());
                    int wo2 = Integer.parseInt(worktime_in.getText());
                    int re2 = Integer.parseInt(resttime_in.getText());

                    int[] save = {co2, wo2, re2};

                    String name = JOptionPane.showInputDialog("請輸入設定檔名稱");
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "儲存失敗！請輸入合適的檔名");
                    } else {
                        JOptionPane.showMessageDialog(null, "儲存成功！\n 設定檔名稱：" + name + "\n 番茄次數：" + co2 + "\n 工作時間：" + wo2 + "\n 休息時間：" + re2);
                        //System.out.println("存檔前選取的欄位"+cbo.getSelectedItem());
                        //System.out.println("存檔前的HMset"+HM.entrySet());
                        //System.out.println("存檔前的HMkeyset"+HM.keySet());
                        //System.out.println(name);
                        //System.out.println(save);
                        for (int p : save) {
                            System.out.println(p);

                        }
                        cbo.addItem(name);
//					System.out.println("cbo add前的欄位"+cbo.getSelectedItem());
//					System.out.println("cbo add前的HMset"+HM.entrySet());
//					System.out.println("cbo add前的HMkeyset"+HM.keySet());

                        HM.put(name, save);
//					System.out.println("-------------");
//					System.out.println("存檔後選取的欄位"+cbo.getSelectedItem());
//					System.out.println("存檔後的HMset"+HM.entrySet());
//					System.out.println("存檔後的HMkeyset"+HM.keySet());
                        FileOutputStream fout = new FileOutputStream("C:/tomato/setting.tomato");
                        ObjectOutputStream oout = new ObjectOutputStream(fout);
                        oout.writeObject(HM);
                        oout.flush();
                        oout.close();
//					System.out.println("-------------");
//					System.out.println("IO後選取的欄位"+cbo.getSelectedItem());
//					System.out.println("IO後的HMset"+HM.entrySet());
//					System.out.println("IO後的HMkeyset"+HM.keySet());
                    }
                    ;

                } catch (NumberFormatException e2) {

                    JOptionPane.showMessageDialog(null, "請輸入正確的自種番茄規格", "番茄難產中", JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException e2) {
                    JOptionPane.showMessageDialog(null, "輸入取消", "番茄難產中", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e2) {
                    System.out.println(e2.toString());
                }
                //int co = Integer.parseInt(count_in.getText());
                //int wo = Integer.parseInt(worktime_in.getText());
                //int re = Integer.parseInt(resttime_in.getText());
                //b1.setVisible(false);
            }
        });

//			b6.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//				
//					try {
//						
//						FileInputStream fin = new FileInputStream("C:/tomato/setting.tomato");
//						ObjectInputStream oin = new ObjectInputStream(fin);
//						Object obj = oin.readObject();
//						
//						String read_name = JOptionPane.showInputDialog("請輸入欲讀取的設定檔名稱");
//						
//						if (obj instanceof HashMap) {
//							HM = (HashMap<String, int[]>)obj;
//							
//						//	count_in.setText(HM.get(read_name));
//						//	worktime_in.setText(HM.get(read_name));
//						//	resttime_in.setText(HM.get(read_name));
//							int[] getHM = HM.get(read_name);
//							
//								count_in.setText(String.format("%d",getHM[0]));
//								worktime_in.setText(String.format("%d",getHM[1]));
//								resttime_in.setText(String.format("%d",getHM[2]));
//							
//						}else {
//							System.out.println("if出問題");
//						}
//						oin.close();
//						
//					} catch (Exception e3) {
//						JOptionPane.showMessageDialog(null, "請輸入正確的設定檔名稱", "番茄難產中", JOptionPane.ERROR_MESSAGE);
//					}
//					//int co = Integer.parseInt(count_in.getText());
//					//int wo = Integer.parseInt(worktime_in.getText());
//					//int re = Integer.parseInt(resttime_in.getText());
//					//b1.setVisible(false);
//				}
//			});


        //先宣告temp 然後拿到當下cbo選取的item
        //刪除cbo上的item ->根據temp
        //刪除HM上的對應key:temp的value
        //try
        //宣告fout oout 寫入HM->oout oout做flush跟close
        //catch


        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("keyset"+HM.keySet());
                //System.out.println("選取的key"+cbo.getSelectedItem());
                //System.out.println("刪除前選取的欄位"+cbo.getSelectedItem());
                //System.out.println("刪除前的HMset"+HM.entrySet());
                //System.out.println("刪除前的HMkeyset"+HM.keySet());

                Object temp = cbo.getSelectedItem();
                //System.out.println(temp.toString());
                try {
                    cbo.removeItem(temp.toString());  //上下對調，沒有使用temp都會出現問題，研究中

                    HM.remove(temp.toString());

                    JOptionPane.showMessageDialog(null, "設定檔： " + temp + " 刪除成功", "吃掉番茄中", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, "刪除失敗，請確認選取設定檔", "吃掉番茄中", JOptionPane.INFORMATION_MESSAGE);
                }
                //System.out.println(temp);
                //System.out.println("刪除後選取的欄位"+cbo.getSelectedItem());
                //System.out.println("刪除後的HMset"+HM.entrySet());
                //System.out.println("刪除後的HMkeyset"+HM.keySet());


                try {
                    FileOutputStream fout = new FileOutputStream("C:/tomato/setting.tomato");
                    ObjectOutputStream oout = new ObjectOutputStream(fout);
                    //System.out.println("keyset"+HM.keySet());
                    oout.writeObject(HM);

                    oout.flush();
                    oout.close();
                } catch (Exception e2) {
                    System.out.println(e2.toString());
                }
                //System.out.println("IO後選取的欄位"+cbo.getSelectedItem());
                //System.out.println("IO後的HMset"+HM.entrySet());
                //System.out.println("IO後的HMkeyset"+HM.keySet());
            }
        });

        //cbo的聆聽事件(評估是否更換itemstatechange)
        //try
        //宣告fin oin obj
        //跑obj的if
        //HM指定obj
        //宣告getHM為HM.get key是cbo當下選的item
        //更改屬性欄位
        //catch

        cbo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //
                try {
                    FileInputStream fin = new FileInputStream("C:/tomato/setting.tomato");
                    ObjectInputStream oin = new ObjectInputStream(fin);
                    Object obj = oin.readObject();
                    if (obj instanceof HashMap) {
                        HM = (HashMap<String, int[]>) obj;

                        int[] getHM = HM.get(cbo.getSelectedItem());

                        count_in.setText(String.format("%d", getHM[0]));
                        worktime_in.setText(String.format("%d", getHM[1]));
                        resttime_in.setText(String.format("%d", getHM[2]));

                    } else {
                        System.out.println("if出問題");
                    }
                } catch (Exception e2) {
                    // TODO: handle exception
                }

                //
            }
        });

        //JP3
        JPanel pan3 = new JPanel();
        add(pan3);
        pan3.setBounds(135, 325, 250, 50);
        //
        pan3.add(cbo);
        pan3.add(b7);
        //pan3.add(b8);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pan3.isVisible()) {
                    pan4.setVisible(false);
                    pan3.setVisible(false);
                } else {
                    pan4.setVisible(true);
                    pan3.setVisible(true);
                }
//					JDialog JD = new JDialog();
//					JOptionPane JOP= new JOptionPane("",JOptionPane.INFORMATION_MESSAGE,JOptionPane.OK_CANCEL_OPTION);					
//					//frm.setContentPane(JOP);
//					JD = JOP.createDialog(frm, "JD的標題");
//					JOP.setInputValue("Hello");
//					JD.setVisible(true);
//					JLabel count2 = new JLabel("番茄次數：");
//					JOP.add(count2);
            }
        });

        //pan3.setBorder(BorderFactory.createLineBorder(Color.black));
        //pan3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JCheckBox Jbox = new JCheckBox("鎖定");
        pan3.add(Jbox);
        pan3.setVisible(false);
        pan3.setOpaque(true);
        pan3.setBackground(getForeground());
        Jbox.setBackground(getForeground());
        Jbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Jbox.isSelected()) {
                    setAlwaysOnTop(true);
                } else {
                    setAlwaysOnTop(false);
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pan2.removeAll();
                pan2.revalidate();
                pan2.repaint();
                // pan3.setVisible(false);
                // pan4.setVisible(false);
                //((TomatoClock) abc).stop();
                try {
                    TomatoClock.stop2();
                } catch (Exception e5) {

                }
                b1.setEnabled(true);
                b3.setEnabled(false);
                b4.setEnabled(true);
                //b5.setEnabled(true);

                count_in.setText(null);
                worktime_in.setText(null);
                resttime_in.setText(null);
                //b1.setVisible(false);
            }
        });
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pan3.isVisible()) {
                    pan3.setVisible(false);
                } else {
                    pan3.setVisible(true);
                }

            }
        });

//			try {
//				imgicon1 = ImageIO.read(new File("C:\\Users\\阿甫\\Downloads\\tomato1.png"));
//			
//			} catch (Exception e) {
//				System.out.println(e.toString());
//			}
//			b1.setIcon(new ImageIcon(imgicon1));

        //b1.setBorderPainted(false);
        //b1.setFocusPainted(false);
        //b1.setBackground(new Color(232,135,159));
        //b1.setForeground(new Color(64,64,64));
        //b1.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN,13));

        //b2.setBorderPainted(false);
        //b2.setFocusPainted(false);
        //b2.setBackground(new Color(232,135,159));
        //b2.setForeground(new Color(64,64,64));
        //b2.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN,13));

        //b3.setBorderPainted(false);
        //b3.setFocusPainted(false);
        //b3.setBackground(new Color(232,135,159));
        //b3.setForeground(new Color(64,64,64));
        //b3.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN,13));

        //b4.setBorderPainted(false);
        //b4.setFocusPainted(false);
        //b4.setBackground(new Color(232,135,159));
        //b4.setForeground(new Color(64,64,64));
        //b4.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN,13));

        //b5.setBorderPainted(false);
        //b5.setFocusPainted(false);
        //b5.setBackground(new Color(232,135,159));
        //b5.setForeground(new Color(64,64,64));
        //b5.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN,13));

        //b6.setBorderPainted(false);
        //b6.setFocusPainted(false);
        //b6.setBackground(new Color(232,135,159));
        //b6.setForeground(new Color(64,64,64));
        //b6.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN,13));

        //b7.setBorderPainted(false);
        //b7.setFocusPainted(false);
        //b7.setBackground(new Color(232,135,159));
        //b7.setForeground(new Color(64,64,64));
        //b7.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN,13));

        //button.setFont(new Font("Serif",Font.BOLD,20));
        //button.setBackground(new Color(0,51,204));//import java.awt.Color;
        //button.setForeground(Color.WHITE);
        //setUndecorated(true);
        //setDefaultLookAndFeelDecorated(true);
        //setOpacity(0.9f);
        //setAlwaysOnTop(true);
        //b6.setFont(new Font("微軟正黑體", Font.PLAIN,12));

//			  try {
//		            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//		                | UnsupportedLookAndFeelException e) {
//		            // TODO Auto-generated catch block
//		            e.printStackTrace();
//		        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new NewTomato();
    }

}

//許願池：自選背景圖、使用者設定儲存在TXT JSON 資料庫 自種番茄　彈出視窗
//待處理bug : 方法多載 按鈕開關邏輯 暫時ok