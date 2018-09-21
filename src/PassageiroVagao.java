
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.awt.event.ActionEvent;

public class PassageiroVagao extends Thread{

	private JFrame frame;
	private JTextField numberOfSeat;
	private JTextField travellingTime;
	private static int seatsInWagon=0;
	private static int travelTime=0;
	boolean wagonAvailable = false;
	boolean deletepassenger = false;
	public static int id = 0;
	public static int alreadyTravelled = 0;
	public static int inTravelling = 0;
	boolean trip = false;
	private JTextField passengerId;
	JLabel lbltravelling = null;
	static PassageiroVagao window = null;
	static Queue<Integer> waitingqueue = new LinkedList<Integer>();
	/**
	 * Lan�ar a aplica��o.
	 */
	public static void main(String[] args) {
		System.out.println("Ol�!!!");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new PassageiroVagao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PassageiroVagao() {
		initialize();
	}

	/*
	 * espere
	 * uma vez que o vag�o estiver com capacidade m�xima ent�o ele pode partir
	 * caso contr�rio o vag�o deve esperar por mais pessoas.
	 */
	public static boolean P()
	{
		if(seatsInWagon <= waitingqueue.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	 * Vag�o est� de novo dispon�vel para a pr�xima viagem.
	 */
	public static boolean V()
	{
		return false;
	}
	
	/**
	 * Inicialize os conte�dos do frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCreateWagon = new JButton("Criar Vag�o");
		btnCreateWagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(trip == true)
				{
					JOptionPane.showMessageDialog(null, "You can't replace the wagon because wagon is in trip");
				}
				else
				{
					if(numberOfSeat.getText().equals("") || travellingTime.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Enter Number of seats and traveeling time properly");
					}
					else
					{
						wagonAvailable = true;
						seatsInWagon = Integer.parseInt(numberOfSeat.getText());
						travelTime = Integer.parseInt(travellingTime.getText());
						System.out.println("Vag�o foi criado");
					}
				}
				
			}
		});
		btnCreateWagon.setBounds(26, 120, 127, 32);
		frame.getContentPane().add(btnCreateWagon);
		
		JLabel lblNuberOfSeat = new JLabel("N� de Assentos");
		lblNuberOfSeat.setBounds(26, 32, 86, 14);
		frame.getContentPane().add(lblNuberOfSeat);
		
		numberOfSeat = new JTextField();
		numberOfSeat.setBounds(151, 29, 86, 20);
		frame.getContentPane().add(numberOfSeat);
		numberOfSeat.setColumns(10);
		
		JLabel lblTravelTime = new JLabel("Viagem em segundos");
		lblTravelTime.setBounds(26, 74, 115, 14);
		frame.getContentPane().add(lblTravelTime);
		
		travellingTime = new JTextField();
		travellingTime.setBounds(151, 71, 86, 20);
		frame.getContentPane().add(travellingTime);
		travellingTime.setColumns(10);
		
		JButton btnDeleteWagon = new JButton("Deletar Vag�o");
		btnDeleteWagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(trip==true || wagonAvailable==false)
				{
					if(trip==true)
					{
						JOptionPane.showMessageDialog(null, "Vag�o Viajando, N�O � poss�vel Deletar!");
					}
					else
						JOptionPane.showMessageDialog(null, "N�O H� Vag�o para Deletar!");
				}
				else if(wagonAvailable==true)
				{
					System.out.println("Vag�o foi deletado!");
					wagonAvailable = false;
				}
			}
		});
		btnDeleteWagon.setBounds(26, 163, 127, 32);
		frame.getContentPane().add(btnDeleteWagon);
		
		JButton btnCreatePassenger = new JButton("Criar Passageiro");
		btnCreatePassenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
				      public void run() {
				    	  id++;
				  		waitingqueue.add(id);
				  		while(wagonAvailable==true && trip==false && P())
				  		{
				  			trip = true;
				  			//wagon is in travel mode
				  			alreadyTravelled = waitingqueue.peek()-1;
				  			inTravelling = alreadyTravelled + seatsInWagon;
				  			System.out.println("Vag�o est� pronto para a viagem!");
				  			ArrayList<Integer> passengerInWagon = new ArrayList<Integer>();
				  			for(int i=0; i<seatsInWagon; i++)
				  			{
				  				passengerInWagon.add(waitingqueue.remove());
				  				System.out.println("Passageiro com ID "+passengerInWagon.get(i)+" est� embarcando, tempo de embarque: "+ new Date());
				  			}
				  			//movement of the wagon
				  			int x = 26;
				  			lbltravelling.setText("==>");
				  			while(x<385)
				  			{
				  				try {
				  					lbltravelling.setLocation(x, 218);
				  					Thread.sleep((travelTime*1000)/718);
				  				} catch (InterruptedException e1) {
				  					// TODO Auto-generated catch block
				  					e1.printStackTrace();
				  				}
				  				x++;
				  			}
				  			lbltravelling.setText("<==");
				  			while(x>26)
				  			{
				  				try {
				  					lbltravelling.setLocation(x, 218);
				  					Thread.sleep((travelTime*1000)/718);
				  				} catch (InterruptedException e1) {
				  					// TODO Auto-generated catch block
				  					e1.printStackTrace();
				  				}
				  				x--;
				  			}
				  			System.out.println("Viagem Encerrada!");
				  			for(int i=0; i<seatsInWagon; i++)
				  			{
				  				System.out.println("Passageiro com ID: "+passengerInWagon.get(i)+" est� desembarcando, tempo de chegada: "+ new Date());
				  			}
				  			trip = V();
				  		}  
				  		Iterator<Integer> waitingQueueIterator = waitingqueue.iterator();
				          while (waitingQueueIterator.hasNext()) {
				          	int p = waitingQueueIterator.next();
				              System.out.println("Passageiro com ID: "+p+" est� Dormindo.");
				          }
				      }
				      
				    }).start();
			}
		});
		btnCreatePassenger.setBounds(268, 28, 141, 32);
		frame.getContentPane().add(btnCreatePassenger);
		
		JButton btnDeletePassenger = new JButton("Deletar Passageiro");
		btnDeletePassenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passengerId.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Entre com o Id do Passageiro que voc� quer remover");
				}
				else
				{
					int enteredId = Integer.parseInt(passengerId.getText());
					if(enteredId<=alreadyTravelled)
					{
						JOptionPane.showMessageDialog(null, "Passageiro j� viajou");
					}
					else if(enteredId<=inTravelling)
					{
						JOptionPane.showMessageDialog(null, "Passageiro est� viajando");
					}
					else if(enteredId<=id)
					{
						JOptionPane.showMessageDialog(null, "Passageiro foi removido da Fila de Espera com Sucesso!");
						waitingqueue.remove(enteredId);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "N�O H� Passageiro com este id "+enteredId+" no Sistema");
					}
				}
			}
		});
		btnDeletePassenger.setBounds(268, 163, 141, 32);
		frame.getContentPane().add(btnDeletePassenger);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(268, 124, 46, 14);
		frame.getContentPane().add(lblId);
		
		passengerId = new JTextField();
		passengerId.setBounds(300, 121, 86, 20);
		frame.getContentPane().add(passengerId);
		passengerId.setColumns(10);
		
		lbltravelling = new JLabel("==>");
		lbltravelling.setBounds(26, 220, 73, 14);
		frame.getContentPane().add(lbltravelling);
	}
}

