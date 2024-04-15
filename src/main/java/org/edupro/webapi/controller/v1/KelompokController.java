package org.edupro.webapi.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.model.request.CommonLembagaReq;
import org.edupro.webapi.model.response.CommonLembagaRes;
import org.edupro.webapi.model.response.Response;
import org.edupro.webapi.service.KelompokMapelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kelompok")
@RequiredArgsConstructor
public class KelompokController extends BaseController<CommonLembagaRes> {
    private final KelompokMapelService kmapelService;

    @GetMapping("/mapel")
    private ResponseEntity<Response> get(){
        var result = kmapelService.get();

        return this.get(result);
    }

    @GetMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> get(@PathVariable("id") Integer id,
                                         @PathVariable("kode") String kode){
        var result = kmapelService.getById(id, kode);
        return getResponse(result);
    }

    @PostMapping("/mapel")
    private ResponseEntity<Response> save(@RequestBody @Valid CommonLembagaReq request){
        var result = kmapelService.save(request);
        return getResponse(result);
    }

    @PutMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> update(@RequestBody @Valid CommonLembagaReq request,
                                            @PathVariable("id") Integer id,
                                            @PathVariable("kode") String kode){
        var result = kmapelService.update(request, id, kode);
        return getResponse(result);
    }

    @DeleteMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> delete(@PathVariable("id") Integer id,
                                            @PathVariable("kode") String kode){
        var result = kmapelService.delete(id, kode);
        return getResponse(result);
    }
}
