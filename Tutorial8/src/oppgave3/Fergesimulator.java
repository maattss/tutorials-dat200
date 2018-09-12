package oppgave3;

import java.util.LinkedList;
import java.util.Queue;

import heap.MinHeap;

public class Fergesimulator {
	
	public static enum Kjoretoytype {BIL, BOBIL, BUSS, LASTEBIL};
	public static enum Hendelsestype {KJORETOY_KOMMER, FERJE_KOMMER}
	
	public static final double SJANSE_BIL = 0.8;
	public static final double SJANSE_BOBIL = 0.9;
	public static final double SJANSE_BUSS = 0.95;
	
	public static final int TID_MELLOM_FERJER = 1800; // 1800 sek == 30 min
	public static final int TID_MELLOM_KJORETOY = 30;	// 30 sek == 1/2 min
	
	public static final int KAPASITET_FERJE = 80;	// Liten: 40, Middels: 80, Stor: 200

	public class Kjoretoy {
		String type;
		int storrelse;
		
		public Kjoretoy(String type, int storrelse) {
			this.type = type;
			this. storrelse = storrelse;
		}
		
		public void skrivUtTilkonsoll() {
			System.out.printf("%-9s Størrelse: %d", type, storrelse);
		}
	}

	public class Hendelse {
		int tidspunkt;
		Hendelsestype hendelsetype;
		
		public Hendelse(int tidspunkt, Hendelsestype hendelsetype) {
			this.tidspunkt = tidspunkt;
			this.hendelsetype = hendelsetype;
		}
	}
	
	MinHeap<Hendelse> hendelseskoe;	//Prioritetskø av hendelser
	Queue<Kjoretoy> kjoretoyko;		//FIFO-kø av kjoretoy
	int nvKoe; 	//Antall plasser brukt på fergeleiet
	int maksKoe;		//Maksimal kø på parkeringsplass i løpet av 1 kjøring
	
	//INIT
	public Fergesimulator() {
		hendelseskoe = new MinHeap<>();
		kjoretoyko = new LinkedList<>();
		Hendelse inithendelse1 = new Hendelse(0, Hendelsestype.KJORETOY_KOMMER);
		Hendelse inithendelse2 = new Hendelse(0, Hendelsestype.FERJE_KOMMER);
		hendelseskoe.insert(0, inithendelse1);
		hendelseskoe.insert(TID_MELLOM_FERJER, inithendelse2);
	}
	
	public void behandleNesteHendelse() {
		int nvTid = 0;
		Hendelse nvHendelse = hendelseskoe.removeMin();
		nvTid = nvHendelse.tidspunkt;
		if(nvHendelse.hendelsetype == Hendelsestype.FERJE_KOMMER) {
			haandterFerjeKommer(nvTid);
		}
		
		if(nvHendelse.hendelsetype == Hendelsestype.KJORETOY_KOMMER) {
			haandterKjoretoyKommer(nvTid);
		}
	}
	
	private void haandterKjoretoyKommer(int nvTid) {
		System.out.println(kjoretoyko.toString());
		double terning = Math.random();
		Kjoretoy kjoretoyet;
		if(terning < SJANSE_BIL) {
			kjoretoyet = new Kjoretoy("Bil", 1);
			kjoretoyko.add(kjoretoyet);
			nvKoe++;
		} else if (SJANSE_BIL <= terning && terning < SJANSE_BOBIL) {
			kjoretoyet = new Kjoretoy("Bobil", 2);
			kjoretoyko.add(kjoretoyet);
			nvKoe += 2;
		} else if (SJANSE_BOBIL <= terning && terning < SJANSE_BUSS) {
			kjoretoyet = new Kjoretoy("Buss", 4);
			kjoretoyko.add(kjoretoyet);
			nvKoe += 4;
		} else { 	//Lastebil kommer
			int tall = (int)(Math.random()*6) + 3;
			kjoretoyet = new Kjoretoy("Lastebil", tall);
			kjoretoyko.add(kjoretoyet);
			nvKoe += tall;
		}
		kjoretoyet.skrivUtTilkonsoll();
		System.out.printf(" Tid: %-6d Kølengde: %d\n", nvTid, nvKoe);
		
		Hendelse nesteHendelse = new Hendelse(nvTid + TID_MELLOM_KJORETOY, Hendelsestype.KJORETOY_KOMMER);
		hendelseskoe.insert(nesteHendelse.tidspunkt, nesteHendelse);
		
	
		
	}
	
	private void haandterFerjeKommer(int nvTid) {
		if(nvKoe > maksKoe) maksKoe = nvKoe;
		System.out.println("\tFerje ankommer! Tid: " + nvTid + " Maks kølengde: " + maksKoe);
		int teller = 0;
		
		while (!(kjoretoyko.isEmpty())) {
			Kjoretoy kjoretoyet = kjoretoyko.peek();
			if ((teller + kjoretoyet.storrelse) > KAPASITET_FERJE) break;
			
			kjoretoyet = kjoretoyko.poll();
			teller += kjoretoyet.storrelse;
			nvKoe -= kjoretoyet.storrelse;
			
			kjoretoyet.skrivUtTilkonsoll();
			System.out.println(" lastes ombord. Kølengde: " + nvKoe);
			
		}
		
		System.out.println("\tFerja kaster loss!\n\tAntall kjøretøyplasser på ferja: " + teller);
		Hendelse nesteHendelse = new Hendelse(nvTid + TID_MELLOM_FERJER, Hendelsestype.FERJE_KOMMER);
		hendelseskoe.insert(nesteHendelse.tidspunkt, nesteHendelse);
	}
	
}
