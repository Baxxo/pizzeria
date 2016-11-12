package it.pizzeria;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Label;

public class Pizzeria {

	protected Shell shell;
	protected Shell dialog;
	protected Shell pizza;
	List list;
	Label lblNewLabel;
	Label lblLabelpizza;
	

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
		Display display = Display.getDefault();
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
	protected void createContents() {
		shell = new Shell();
		shell.setSize(632, 463);
		shell.setText("SWT Application");

		pizza = new Shell();
		pizza.setText("Pizza");
		pizza.setSize(200, 200);

		dialog = new Shell();
		dialog.setText("Cliente");
		dialog.setSize(200, 200);

		ListaPizze lp = new ListaPizze();
		Pizzaiolo p = new Pizzaiolo(lp);
		

		lblNewLabel = new Label(pizza, SWT.NONE);
		lblNewLabel.setBounds(0, 50, 200, 200);

		lblLabelpizza = new Label(dialog, SWT.NONE);
		lblLabelpizza.setBounds(80, 43, 55, 15);

		Button btnAvviaPizzeria = new Button(shell, SWT.NONE);
		btnAvviaPizzeria.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);

				int pizzCur = list.getSelectionIndex();
				if (pizzCur < 0) {
					messageBox.setText("pizza");
					messageBox.setMessage("nessuna pizza");
					messageBox.open();
					p.run("nessuna pizza");
				} else {
					messageBox.setText("pizza");
					messageBox.setMessage(lp.getPizza(pizzCur));
					messageBox.open();
					p.run(lp.getPizza(pizzCur));
				}
			}
		});
		
	
		btnAvviaPizzeria.setBounds(10, 10, 75, 25);
		btnAvviaPizzeria.setText("Avvia Pizzeria");

		Button btnNuovoCliente = new Button(shell, SWT.NONE);
		btnNuovoCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Cliente c1 = new Cliente(lp);
				int pizzCur = list.getSelectionIndex();
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
				messageBox.setMessage(lp.getPizza(pizzCur));
				messageBox.open();
				c1.start();
				

			}
		});
		btnNuovoCliente.setBounds(518, 10, 88, 25);
		btnNuovoCliente.setText("Nuovo Cliente");

		list = new List(shell, SWT.BORDER);
		list.setBounds(10, 112, 145, 203);
		list.setItems(lp.pizze);

		List list_1 = new List(shell, SWT.BORDER);
		list_1.setBounds(233, 112, 145, 203);

		List list_2 = new List(shell, SWT.BORDER);
		list_2.setBounds(461, 112, 145, 203);

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(186, 112, 2, 203);

		Label label_1 = new Label(shell, SWT.SEPARATOR);
		label_1.setBounds(418, 112, 2, 203);
	}
}
