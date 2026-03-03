package Mattiazerbini.backend.payloads;

import java.time.LocalTime;

public class SlotPrenotazioni {
    private LocalTime inizio;
    private LocalTime fine;

    public SlotPrenotazioni(LocalTime inizio, LocalTime fine) {
        this.inizio = inizio;
        this.fine = fine;
    }

    public LocalTime getFine() {
        return fine;
    }

    public LocalTime getInizio() {
        return inizio;
    }
}
