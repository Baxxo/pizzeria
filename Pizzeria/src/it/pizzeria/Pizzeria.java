package it.pizzeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineBackgroundListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.wb.swt.SWTResourceManager;

public class Pizzeria {

	protected Shell shell;
	protected Shell dialog;
	protected Shell pizza;
	public List list;
	public List list_1;
	public List list_2;
	Label lblNewLabel;
	Label lblLabelpizza;
	public String pizze[] = { "Margherita", "Capricciosa", "4 Stagioni", "Wurstel" };
	Cliente[] c = new Cliente[100];
	public int cur = 0;
	ListaPizze lp;
	Pizzaiolo p;
	Pizzaiolo p2;
	int pizzCur;
	Display display;
	String nome;
	private Text text;
	Label lblPizzaiolo_1;
	ProgressBar progressBar_1;
	ProgressBar progressBar;
	Audio a = new Audio();
	Label forno;
	Label lblPizza;
	Label lblF;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Pizzeria window = new Pizzeria();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */

	public void addList(String s) {
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				list_2.add(s);
			}
		});
	}

	public void forno(String s) {
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				lblF.setText(s);
				switch (s) {
				case "Margherita":
					lblPizza.setImage(SWTResourceManager.getImage(Pizzeria.class, "/it/pizzeria/margherita.png"));
					break;
				case "Capricciosa":
					lblPizza.setImage(SWTResourceManager.getImage(Pizzeria.class, "/it/pizzeria/capricciosa.png"));
					break;
				case "4 Stagioni":
					lblPizza.setImage(SWTResourceManager.getImage(Pizzeria.class, "/it/pizzeria/4Stagioni.png"));
					break;
				case "Wurstel":
					lblPizza.setImage(SWTResourceManager.getImage(Pizzeria.class, "/it/pizzeria/wurstel.png"));
					break;
				case "":
					lblPizza.setText("");
					break;
				}

			}
		});
	}

	public void togliPizza() {
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				list_1.remove(0);

			}
		});

	}

	public void updateBar(int n) {
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < n; i++) {
					progressBar.setSelection(i);
				}
			}
		});

	}

	public void updateBar2(int n) {
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < n; i++) {
					progressBar_1.setSelection(i);
				}
			}
		});
	}

	protected void createContents() {
		lp = new ListaPizze(this);
		p = new Pizzaiolo(lp);
		p.setName("primo");
		p.start();
		p2 = new Pizzaiolo(lp);

		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(Pizzeria.class, "/it/pizzeria/download.png"));
		shell.setSize(632, 527);
		shell.setText("SWT Application");

		pizza = new Shell();
		pizza.setText("Pizza");
		pizza.setSize(200, 200);

		dialog = new Shell();
		dialog.setText("Cliente");
		dialog.setSize(200, 200);

		list = new List(shell, SWT.BORDER);
		list.setBounds(10, 209, 170, 70);
		list.setItems(pizze);

		lblNewLabel = new Label(pizza, SWT.NONE);
		lblNewLabel.setBounds(0, 50, 200, 200);

		lblLabelpizza = new Label(dialog, SWT.NONE);
		lblLabelpizza.setBounds(80, 43, 55, 15);

		list_1 = new List(shell, SWT.BORDER);
		list_1.setBounds(231, 225, 145, 203);

		list_2 = new List(shell, SWT.BORDER);
		list_2.setBounds(461, 225, 145, 203);

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(186, 198, 2, 230);

		Label label_1 = new Label(shell, SWT.SEPARATOR);
		label_1.setBounds(418, 198, 2, 230);

		Button btnAvviaPizzeria = new Button(shell, SWT.NONE);
		btnAvviaPizzeria.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				a.setSound("ALARMCLOCK");
				a.suona();
				lblPizzaiolo_1.setVisible(true);
				progressBar_1.setVisible(true);
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
				messageBox.setMessage("Hei ciao! sono un altro pizzaiolo");
				messageBox.open();
				int pizzCur = list.getSelectionIndex();
				p2.setName("sec");
				p2.start();
				btnAvviaPizzeria.setVisible(false);
			}
		});

		btnAvviaPizzeria.setBounds(10, 138, 109, 25);
		btnAvviaPizzeria.setText("Assumi pizzaiolo");

		Button btnNuovoCliente = new Button(shell, SWT.NONE);
		btnNuovoCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				a.setSound("Doorbell");
				a.suona();
				c[cur] = new Cliente(lp);
				pizzCur = list.getSelectionIndex();

				if (text.getText().equals("")) {
					nome = "billo";
				} else {
					nome = text.getText();
				}
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
				messageBox.setText(nome);
				messageBox.setMessage(pizze[pizzCur]);
				messageBox.open();
				list_1.add(pizze[pizzCur]);
				c[cur].setPizza(pizze[pizzCur]);
				c[cur].start();
				cur++;

			}
		});
		btnNuovoCliente.setBounds(298, 138, 109, 25);
		btnNuovoCliente.setText("Nuovo Cliente");

		forno = new Label(shell, SWT.NONE);
		forno.setAlignment(SWT.CENTER);
		forno.setBounds(10, 285, 170, 15);
		forno.setText("Forno");

		Label lblMen = new Label(shell, SWT.NONE);
		lblMen.setAlignment(SWT.CENTER);
		lblMen.setBounds(10, 187, 170, 15);
		lblMen.setText("Men\u00F9");

		Label lblPizzeDaFare = new Label(shell, SWT.NONE);
		lblPizzeDaFare.setBounds(265, 187, 72, 15);
		lblPizzeDaFare.setText("Pizze da fare");

		Label lblPizzePronte = new Label(shell, SWT.NONE);
		lblPizzePronte.setBounds(494, 187, 72, 15);
		lblPizzePronte.setText("Pizze pronte");

		Label lblCliente = new Label(shell, SWT.NONE);
		lblCliente.setAlignment(SWT.CENTER);
		lblCliente.setBounds(424, 143, 42, 21);
		lblCliente.setText("Cliente:");

		text = new Text(shell, SWT.BORDER);
		text.setText("");
		text.setBounds(495, 140, 111, 21);

		progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(144, 460, 150, 17);

		progressBar_1 = new ProgressBar(shell, SWT.NONE);
		progressBar_1.setBounds(382, 460, 150, 17);
		progressBar_1.setVisible(false);

		Label lblPizzaiolo = new Label(shell, SWT.NONE);
		lblPizzaiolo.setAlignment(SWT.CENTER);
		lblPizzaiolo.setBounds(66, 460, 72, 15);
		lblPizzaiolo.setText("Pizzaiolo1: ");

		lblPizzaiolo_1 = new Label(shell, SWT.NONE);
		lblPizzaiolo_1.setAlignment(SWT.CENTER);
		lblPizzaiolo_1.setBounds(304, 460, 72, 15);
		lblPizzaiolo_1.setText("Pizzaiolo2: ");
		lblPizzaiolo_1.setVisible(false);

		lblPizza = new Label(shell, SWT.NONE);
		lblPizza.setAlignment(SWT.CENTER);
		lblPizza.setBounds(10, 322, 170, 107);

		Label lblInsegna = new Label(shell, SWT.NONE);
		lblInsegna.setAlignment(SWT.CENTER);
		lblInsegna.setImage(SWTResourceManager.getImage(Pizzeria.class, "/it/pizzeria/download.png"));
		lblInsegna.setBounds(0, 10, 616, 122);

		lblF = new Label(shell, SWT.NONE);
		lblF.setText("");
		lblF.setAlignment(SWT.CENTER);
		lblF.setBounds(10, 301, 170, 15);

	}
}
