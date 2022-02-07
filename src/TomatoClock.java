

import java.awt.GraphicsEnvironment;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//自行新增
//import tw.brad.myjava.NewTomato;
import java.awt.Window;
//import tw.brad.myjava.TomatoClock.CoffeeTask;
//
//import tw.brad.myjava.TomatoClockCustom.endTask;


public class TomatoClock extends JLabel {
    private static Timer timer; //static
    private CoffeeTask clockTask;
    private CoffeeTaskcos clockTask2;
    public String ans;
    public int count = 1, co, wo, re;

    public int ss = 00, mm; // 預設00 25

    public TomatoClock() {
        mm = 25;
        timer = new Timer();
        clockTask = new CoffeeTask(); //this
        timer.schedule(clockTask, 0, 1 * 1000);
        // -----------
        endTask endTask = new endTask(timer);
        timer.schedule(endTask, 150000 * 1000); //1500
        // -----------
        //CoffeeTask coffeeTask = new CoffeeTask();
        //timer.schedule(clockTask,300*1000);

    }

    public TomatoClock(int co, int wo, int re) {
        this.co = co;
        this.wo = wo;
        this.re = re;
        this.mm = wo;

        timer = new Timer();
        clockTask2 = new CoffeeTaskcos(); //this
        timer.schedule(clockTask2, 0, 1 * 1000);
        // -----------
        endTask endTask = new endTask(timer);
        timer.schedule(endTask, 150000 * 1000); //1500
        // -----------
        //CoffeeTask coffeeTask = new CoffeeTask();
        //timer.schedule(clockTask,300*1000);

    }

    public void stop() {
        timer.cancel();
        timer.purge();
        timer = null;
    }

    public static void stop2() {
        timer.cancel();
        timer.purge();
        timer = null;
    }


    public class endTask extends TimerTask {
        private Timer timer;

        public endTask(Timer timer) {
            this.timer = timer;
        }

        @Override
        public void run() {
            timer.cancel();
            timer.purge();
            timer = null;
            JOptionPane.showMessageDialog(null, "吃番茄時間到囉");
        }
    }

    public class CoffeeTask extends TimerTask {
        int round;

        @Override
        public void run() {
            round = count % 2;
            switch (round) {
                case 1:
                    if (ss == 0 && mm == 0 && (count % 8) == 0) {
                        JOptionPane.showMessageDialog(null, "大番茄時間(灑花)");
                        mm += 30; //30
                        count++;
                        System.out.println("count數: " + count);

                    } else if (ss == 0 && mm == 0) {
                        //ring ring = new ring();
                        //ring.stopring();
                        new filetest();
                        //requestFocus();

                        JOptionPane.showMessageDialog(null, "番茄時間到囉");
                        //
                        //final JDialog dialog = new JDialog();
                        //dialog.setAlwaysOnTop(true);
                        //JOptionPane.showMessageDialog(dialog, "番茄時間到囉");
                        //

//					JOptionPane optionPane = new JOptionPane();
//					JDialog dialog = optionPane.createDialog("Title");
//					dialog.setAlwaysOnTop(true);
//					dialog.setVisible(true);

                        //
                        mm += 5; //5
                        count++;
                        System.out.println("count數: " + count);
                    } else if (ss == 0) {
                        ss = 59;
                        mm--;
                    } else ss--;
                    break;

                case 0:
                    if (ss == 0 && mm == 0) {
                        JOptionPane.showMessageDialog(null, "重新準備番茄");
                        mm += 25;//25
                        count++;
                        System.out.println("count數: " + count);

                    } else if (ss == 0) {
                        ss = 59;
                        mm--;
                    } else ss--;
                    break;

                default:
                    System.out.println("error");
            }
            setText(String.format("%02d:%02d", mm, ss));

        }
    }

    public class CoffeeTaskcos extends TimerTask {
        int round;

        @Override
        public void run() {
            System.out.println(count);
            round = count % 2;
            System.out.println(round);
            if (co * 2 >= count) {
                switch (round) {
                    case 1:
//					if((count) == 100) {
//						new filetest();
//						System.out.println("有執行到");
//						JOptionPane.showMessageDialog(null, "大番茄時間(灑花)");
//						mm+=30; //30
//						count++;
//				}
                        if (ss == 0 && mm == 0) {
                            //ring ring = new ring();
                            //ring.stopring();

                            //requestFocus();


                            if ((count % 8 + 1) == 8) {

                            } else {
                                new filetest();
                                final JDialog dialog = new JDialog();
                                dialog.setAlwaysOnTop(true);
                                JOptionPane.showMessageDialog(dialog, "番茄時間到囉");
                            }
                            //JOptionPane optionPane = new JOptionPane("番茄時間到囉");
                            //JDialog dialog = optionPane.createDialog("番茄熱身中");
                            //dialog.setAlwaysOnTop(true);
                            //dialog.setVisible(true);

                            //final JDialog dialog = new JDialog();
                            //dialog.setAlwaysOnTop(true);
                            //JOptionPane.showMessageDialog(dialog, "新番茄時間到囉");
                            //JOptionPane.showMessageDialog(null, "番茄時間到囉");

                            mm += re; //resttime
                            count++;
                        } else if (ss == 0) {
                            ss = 59;
                            mm--;
                        } else ss--;
                        break;

                    case 0:
                        if ((count % 8) == 0) {
                            new filetest();
                            System.out.println("有執行到");
                            JOptionPane.showMessageDialog(null, "大番茄時間(灑花)");
                            mm += 30; //30
                            count++;
                        } else if (ss == 0 && mm == 0) {
                            new filetest();
                            final JDialog dialog = new JDialog();
                            dialog.setAlwaysOnTop(true);
                            JOptionPane.showMessageDialog(dialog, "重新準備番茄");
                            //JOptionPane.showMessageDialog(null, "重新準備番茄");
                            mm += wo;//worktime
                            count++;

                        } else if (ss == 0) {
                            ss = 59;
                            mm--;
                        } else ss--;
                        break;

                    default:
                        System.out.println("error");
                }
                setText(String.format("%02d:%02d", mm, ss));
            } else {
                stop();
                setText(String.format("%02d:%02d", 0, 0));
            }
        }
    }
}


	
		
	