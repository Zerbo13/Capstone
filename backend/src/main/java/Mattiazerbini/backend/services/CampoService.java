package Mattiazerbini.backend.services;

import Mattiazerbini.backend.Excenptions.NotFoundException;
import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Servizio;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.repositories.CampoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Slf4j

public class CampoService {

    public final CampoRepository campoRepository;

    @Autowired

    public CampoService(CampoRepository campoRepository) {
        this.campoRepository = campoRepository;
    }

    @Autowired
    public CloudinaryService cloudinaryService;

    public Campo salvaCampo(CampoPayload payload){

        Campo newCampo = new Campo(
                payload.getNome(),
                payload.getDescrizione(),
                payload.isCoperto(),
                payload.getPrezzoOra(),
                payload.isAttivo(),
                payload.getImmagine(),
                payload.getTipo()
        );
        Campo campoSalvato = this.campoRepository.save(newCampo);
        log.info("Il campo "+newCampo.getNome()+ " è stato inserito con successo!");
        return campoSalvato;
    }

    //FIND ALL
    public Page<Campo> findAll(int page, int size, String sortBy) {
        if (page <= 0) page = 0;
        if (size < 0 || size > 150) size = 10;
        if (sortBy == null) sortBy = "nome";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return campoRepository.findAll(pageable);
    }

    //FIND BY ID
    public Campo findById(long idCampo) {
        return campoRepository.findById(idCampo)
                .orElseThrow(() -> new NotFoundException("Il campo con id " + idCampo + " non è stato trovato"));
    }

    //ELIMINA UTENTE
    public void findByIdAndDelete(long idCampo) {
        Campo found = this.findById(idCampo);
        this.campoRepository.delete(found);
        log.info("Il campo con id "+found.getId()+ " è stato eliminata con successo!");
    }

    //MODIFICA UTENTE
    public Campo findByIdAndUpdate(long idCampo, CampoPayload payload) {

        Campo found = this.findById(idCampo);
        found.setNome(payload.getNome());
        found.setDescrizione(payload.getDescrizione());
        found.setCoperto(payload.isCoperto());
        found.setPrezzoOra(payload.getPrezzoOra());
        found.setAttivo(payload.isAttivo());
        found.setTipo(payload.getTipo());
        found.setImmagine(payload.getImmagine());


        Campo campoModificato = this.campoRepository.save(found);
        log.info("Il campo con id " + campoModificato.getId() + " è stato modificato correttamente");
        return campoModificato;
    }

    public Campo uploadImmagine(Long id, MultipartFile file) throws IOException {
        Campo campo = campoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campo con id " +id+ "non trovato"));
        String url = cloudinaryService.uploadImage(file);
        campo.setImmagine(url);
        return  campoRepository.save(campo);
    }
}
