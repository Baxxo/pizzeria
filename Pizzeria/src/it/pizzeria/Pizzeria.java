package it.pizzeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineBackgroundListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Label;

public class Pizzeria {

	protected Shell shell;
	protected Shell dialog;
	protected Shell pizza;
	public List list;
	public List list_1;
	public List list_2;
	Label lblNewLabel;
	Label lblLabelpizza;
	public String pizze[] = { "Margherita", "Capricciosa", "4 Formaggi", "Wurstel" };
	Cliente[] c = new Cliente[100];
	public int cur = 0;
	ListaPizze lp;
	Pizzaiolo p;
	Pizzaiolo p2;
	int pizzCur;
	Display display;
	Label forno;

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
	
	public void forno(String s){
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				forno.setText(s);
			}
		});
	}

	protected void createContents() {
		lp = new ListaPizze(this);
		p = new Pizzaiolo(lp);
		p.start();
		p2 = new Pizzaiolo(lp);

		shell = new Shell();
		shell.setSize(632, 463);
		shell.setText("SWT Application");

		pizza = new Shell();
		pizza.setText("Pizza");
		pizza.setSize(200, 200);

		dialog = new Shell();
		dialog.setText("Cliente");
		dialog.setSize(200, 200);

		list = new List(shell, SWT.BORDER);
		list.setBounds(10, 139, 145, 70);
		list.setItems(pizze);

		lblNewLabel = new Label(pizza, SWT.NONE);
		lblNewLabel.setBounds(0, 50, 200, 200);

		lblLabelpizza = new Label(dialog, SWT.NONE);
		lblLabelpizza.setBounds(80, 43, 55, 15);

		list_1 = new List(shell, SWT.BORDER);
		list_1.setBounds(231, 139, 145, 203);

		list_2 = new List(shell, SWT.BORDER);
		list_2.setBounds(461, 139, 145, 203);

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(186, 112, 2, 203);

		Label label_1 = new Label(shell, SWT.SEPARATOR);
		label_1.setBounds(418, 112, 2, 203);

		Button btnAvviaPizzeria = new Button(shell, SWT.NONE);
		btnAvviaPizzeria.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
				messageBox.setMessage("Hei ciao! sono un altro pizzaiolo");
				messageBox.open();
				int pizzCur = list.getSelectionIndex();
				p2.start();
			}
		});

		btnAvviaPizzeria.setBounds(10, 10, 109, 25);
		btnAvviaPizzeria.setText("Assumi pizzaiolo");

		Button btnNuovoCliente = new Button(shell, SWT.NONE);
		btnNuovoCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				c[cur] = new Cliente(lp);
				pizzCur = list.getSelectionIndex();

				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
				messageBox.setMessage(pizze[pizzCur]);
				messageBox.open();

				list_1.add(pizze[pizzCur]);

				c[cur].setPizza(pizze[pizzCur]);
				c[cur].start();
				cur++;

			}
		});
		btnNuovoCliente.setBounds(10, 51, 109, 25);
		btnNuovoCliente.setText("Nuovo Cliente");
		
		Label lblForno = new Label(shell, SWT.NONE);
		lblForno.setAlignment(SWT.CENTER);
		lblForno.setBounds(10, 215, 145, 15);
		lblForno.setText("Forno");
		
		forno = new Label(shell, SWT.NONE);
		forno.setBounds(10, 236, 145, 79);
		Label lblMen = new Label(shell, SWT.NONE);
		lblMen.setBounds(49, 101, 55, 15);
		lblMen.setText("Men\u00F9");
		
		Label lblPizzeDaFare = new Label(shell, SWT.NONE);
		lblPizzeDaFare.setBounds(265, 101, 72, 15);
		lblPizzeDaFare.setText("Pizze da fare");
		
		Label lblPizzePronte = new Label(shell, SWT.NONE);
		lblPizzePronte.setBounds(494, 101, 72, 15);
		lblPizzePronte.setText("Pizze pronte");

	}
}
