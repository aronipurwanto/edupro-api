package org.edupro.webapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.Constant;
import org.edupro.webapi.model.entity.LookupEntity;
import org.edupro.webapi.repository.LookupRepo;
import org.edupro.webapi.util.CommonUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final LookupRepo lookupRepo;

    @Override
    public void run(String... args) throws Exception {
        initResourceType();
        initAgama();
        initWargaNegara();
        initSemester();
        initGender();
        initGolDarah();
        initAbsensi();
        initLevelKelas();
        initAttachmentType();
        initJenisIbadahOpsi();
        initJenisIbadahCheck();
    }

    private void initResourceType(){
        int count = lookupRepo.countAllByGroup(Constant.ResourceType.TYPE);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.ResourceType.TYPE, Constant.ResourceType.MATERIAL,Constant.ResourceType.MATERIAL,1),
                new LookupEntity(CommonUtil.getUUID(), Constant.ResourceType.TYPE, Constant.ResourceType.QUESTION,Constant.ResourceType.QUESTION,2),
                new LookupEntity(CommonUtil.getUUID(), Constant.ResourceType.TYPE, Constant.ResourceType.QUIZ,Constant.ResourceType.QUIZ,3)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save ResourceType is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initAgama(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_AGAMA);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "ISLAM","Islam",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KRISTEN","Kristen",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KATHOLIK","Katholik",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "HINDU","Hindu",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "BUDHA","Budha",5),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KONG_HU_CHU","Kong Hu Chu",6),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KEPERCAYAAN","Kepercayaan",7),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "LAINNYA","Lainnya",8)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Agama is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initWargaNegara(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_WN);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_WN, "WNI","Warga Negara Indonesia",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_WN, "WNA","Warga Negara Asing",2)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Warga Negara is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initSemester(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_SEMESTER);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_SEMESTER, "1","Semester Ganjil",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_SEMESTER, "2","Semester Genap",2)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Semester is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initGender(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_GENDER);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GENDER, "PRIA","Pria",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GENDER, "WANITA","Wanita",2)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Gender is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initGolDarah(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_GOL_DARAH);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "A","A",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "B","B",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "AB","AB",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "O","O",4)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Gol Darah is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }


    private void initAbsensi(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_ABSENSI);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "HADIR","Hadir",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "IJIN","Ijin",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "SAKIT","Sakit",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "ALPHA","Alpha",4)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Absendi is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }


    private void initAttachmentType(){
        int count = lookupRepo.countAllByGroup(Constant.AttachmentType.TYPE);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.DRIVE,Constant.AttachmentType.DRIVE,1),
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.YOUTUBE,Constant.AttachmentType.YOUTUBE,2),
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.UPLOAD_FILE,Constant.AttachmentType.UPLOAD_FILE,3),
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.LINK,Constant.AttachmentType.LINK,4)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Attachment Type is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initLevelKelas(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_LEVEL_KELAS);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "TK_01","TK Tahun 1",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "TK_02","TK Tahun 2",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_01","SD Kelas 1",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_02","SD Kelas 2",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_03","SD Kelas 3",5),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_04","SD Kelas 4",6),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_05","SD Kelas 5",7),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_06","SD Kelas 6",8),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMP_07","SMP Kelas 7",9),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMP_08","SMP Kelas 8",10),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMP_09","SMP Kelas 9",11),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMA_10","SMA Kelas 10",12),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMA_11","SMA Kelas 11",13),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMA_12","SMA Kelas 12",14)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Level Kelas is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initJenisIbadahOpsi(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_IBADAH_OPSI);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "SHOLAT_DHUHA","Sholat Dhuha",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "MUROJAAH_PAGI","Murojaah Pagi",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "MAJELIS_PAGI","Majelis Pagi",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "SHOLAT_ZHUHUR","Sholat Zhuhur",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "LITERASI_SIANG","Literasi Siang",5),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "SHOLAT_ASHAR","Sholat Ashar",6),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "LITERASI_SORE","Literasi Sore",7)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Ibadah Opsi is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initJenisIbadahCheck(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_IBADAH_CHECK);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_CHECK, "IBADAH_CHECK_01","Memimpin Sholat Dhuha dan Doa",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_02","Memimpin Al Ma tsurat",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_03","Memimpin Tilawah /  Murojaah",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_04","Terakhir Murojaah",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_05","Pendamping",5)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Ibadah Check is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
