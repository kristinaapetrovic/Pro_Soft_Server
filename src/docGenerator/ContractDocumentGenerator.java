package docGenerator;

import model.Projekat;
import model.Sponzor;
import model.Aktivnost;
import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.JeSponzor;

public class ContractDocumentGenerator {

    public static void generateContractDoc(Projekat projekat, List<JeSponzor> jeSponzor, List<Aktivnost> aktivnosti, String filePath) throws IOException {
        XWPFDocument document = new XWPFDocument();

       
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("UGOVOR");
        titleRun.setBold(true);
        titleRun.setFontSize(20);

       
        XWPFParagraph contractDetails = document.createParagraph();
        XWPFRun detailsRun = contractDetails.createRun();
        detailsRun.setText("Broj ugovora: " + projekat.getRegBroj());
        detailsRun.addBreak();
        detailsRun.setText("Naziv projekta: " + projekat.getNazivProjekta());
        detailsRun.addBreak();
        detailsRun.setText("Budzet: " + projekat.getBudzet());
        detailsRun.addBreak();
        detailsRun.setText("Trajanje u satima: " + projekat.getTrajanje());
        detailsRun.addBreak();
        detailsRun.setText("Datum podetka: " + projekat.getDatumPocetka());
        detailsRun.addBreak();
        detailsRun.setText("Datum zavrsetka: " + projekat.getDatumZavrsetka());
        detailsRun.addBreak();
        detailsRun.setText("Menadzer: " + projekat.getMenadzer().getImePrezime() + ", " + projekat.getMenadzer().getJmbg());
        detailsRun.addBreak();
        detailsRun.setText("Opis mprojekta: " + projekat.getOpisProjekta());
        detailsRun.addBreak();

        
        XWPFParagraph sponsorTitle = document.createParagraph();
        sponsorTitle.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun sponsorTitleRun = sponsorTitle.createRun();
        sponsorTitleRun.setText("Sponzori projekta:");
        sponsorTitleRun.setBold(true);

        XWPFTable sponsorTable = document.createTable();
        XWPFTableRow sponsorHeader = sponsorTable.getRow(0);
        sponsorHeader.getCell(0).setText("Maticni broj");
        sponsorHeader.addNewTableCell().setText("Vlasnik");
        sponsorHeader.addNewTableCell().setText("Firma");
        sponsorHeader.addNewTableCell().setText("Robni");
        sponsorHeader.addNewTableCell().setText("Novcani");
        sponsorHeader.addNewTableCell().setText("Iznos");

        for (JeSponzor js : jeSponzor) {
            XWPFTableRow row = sponsorTable.createRow();
            row.getCell(0).setText(js.getSponzor().getMaticniBroj());
            row.getCell(1).setText(js.getSponzor().getVlasnik());
            row.getCell(2).setText(js.getSponzor().getNazivFirme());
            row.getCell(3).setText(js.isRobni() ? "Da" : "Ne");
            row.getCell(4).setText(js.isNovcani() ? "Da" : "Ne");
            row.getCell(5).setText(js.getIznos() + "");
        }

        
        XWPFParagraph activityTitle = document.createParagraph();
        activityTitle.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun activityTitleRun = activityTitle.createRun();
        activityTitleRun.setText("Aktivnosti projekta");
        activityTitleRun.setBold(true);

        XWPFTable activityTable = document.createTable();
        XWPFTableRow activityHeader = activityTable.getRow(0);
        activityHeader.getCell(0).setText("Redni broj");
        activityHeader.addNewTableCell().setText("Naziv");
        activityHeader.addNewTableCell().setText("Opis");
        activityHeader.addNewTableCell().setText("Datum realizacije");

        for (Aktivnost aktivnost : aktivnosti) {
            XWPFTableRow row = activityTable.createRow();
            row.getCell(0).setText(aktivnost.getRedniBroj()+"");
            row.getCell(1).setText(aktivnost.getNazivAktivnosti());
            row.getCell(2).setText(aktivnost.getOpisAktivnosti());
            row.getCell(2).setText(aktivnost.getDatumRealizacije()==null?"":aktivnost.getDatumRealizacije()+"");

        }

        
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            document.write(out);
        }
        document.close();
    }
}
