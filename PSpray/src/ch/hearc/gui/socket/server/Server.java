
package ch.hearc.gui.socket.server;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

import ch.hearc.b_poo.z_tools.Threads;
import ch.hearc.c_gui.z_tools.image.BImageSerializable;
import ch.hearc.e_socket.bin.a_tools.status.listener.StatusAdapter;
import ch.hearc.e_socket.bin.a_tools.status.listener.StatusListener;
import ch.hearc.e_socket.bin.a_tools.status.listener.error.ErrorEvent;
import ch.hearc.e_socket.bin.a_tools.status.listener.success.SuccessEvent;
import ch.hearc.e_socket.bin.b_api.server.SocketServer;
import ch.hearc.e_socket.bin.b_api.server.communication.Transformer_I;

/**
 * color -> level gray
 */
public class Server
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void start(String tITLE, int pORT)
		{
		SocketServer<BImageSerializable, BImageSerializable> server = new SocketServer<BImageSerializable, BImageSerializable>(createSupplier(), tITLE, pORT);

		server.addStatusListener(createStatusListener());

		// Start
		server.start(); // async

		Threads.sleepMS(10 * 1000 * 6 * 2); // disons

		// Stop after LIFE_TIME_SERVER_MS
		server.stop(); // en pratique on ne l'arrete jamais
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static Supplier<Transformer_I<BImageSerializable, BImageSerializable>> createSupplier()
		{
		return new Supplier<Transformer_I<BImageSerializable, BImageSerializable>>()
			{

			@Override
			public Transformer_I<BImageSerializable, BImageSerializable> get()
				{
				return createTransformer();
				}
			};
		}

	private static Transformer_I<BImageSerializable, BImageSerializable> createTransformer()
		{
		return new Transformer_I<BImageSerializable, BImageSerializable>()
			{

			@Override
			public BImageSerializable transform(BImageSerializable serializable)
				{
				BufferedImage inputImage = serializable.bimage();

				ColorToLevelgray converter = new ColorToLevelgray(inputImage);
				converter.run();

				return new BImageSerializable(converter.getbImage());
				}
			};
		}

	private static StatusListener createStatusListener()
		{
		return new StatusAdapter()
			{

			@Override
			public void onSuccess(SuccessEvent successEvent)
				{
				System.out.println(successEvent);
				}

			@Override
			public void onError(ErrorEvent errorEvent)
				{
				System.err.println(errorEvent);
				}
			};
		}

	}


