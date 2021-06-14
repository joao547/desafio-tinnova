package com.desafio.tinnova.controller;

import com.desafio.tinnova.controller.dto.VeiculoDTO;
import com.desafio.tinnova.controller.exception.MarcaException;
import com.desafio.tinnova.model.Veiculo;
import com.desafio.tinnova.service.VeiculoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin("*")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @ApiOperation(value = "Busca o veiculo pelo id",
            notes = ".getVeiculo()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Veiculos encontrados com sucesso"),
            @ApiResponse(code = 204, message = "Não foi possível encontrar os veiculos"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", required = true, paramType = "path",
                    value = "Id do veiculo", dataType = "Long"),
    })
    @GetMapping("/{id}")
    private ResponseEntity getVeiculo(@PathVariable("id") Long id){
        try {
            Veiculo veiculo = veiculoService.getById(id);
            if (veiculo == null)
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(veiculo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Busca todos os veiculos ",
            notes = ".listAll()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Veiculos encontrados com sucesso"),
            @ApiResponse(code = 204, message = "Não foi possível encontrar os veiculos"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @GetMapping
    private ResponseEntity listAll(){
        List<Veiculo> veiculos = veiculoService.getAll();
        if (veiculos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(veiculos);
    }

    @ApiOperation(value = "Cria um veiculo",
            notes = ".createVeiculo()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Veiculo criado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível criar o veiculo"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "veiculoDTO", required = true, paramType = "body",
                    value = "Informações do veiculo", dataType = "VeiculoDTO"),
    })
    @PostMapping
    private ResponseEntity createVeiculo(@RequestBody VeiculoDTO veiculoDTO){
        try {
            Veiculo veiculo = veiculoService.create(veiculoDTO);
            return ResponseEntity.ok(veiculo);
        } catch (MarcaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Atualizar um veiculo",
            notes = ".updateVeiculo()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Veiculo atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível atualizar o veiculo"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "veiculoDTO", required = true, paramType = "body",
                    value = "Informações do veiculo", dataType = "VeiculoDTO"),
    })
    @PutMapping("/{id}")
    private ResponseEntity updateVeiculo(@PathVariable("id") Long id, @RequestBody VeiculoDTO veiculoDTO){
        try {
            Veiculo veiculo = veiculoService.update(veiculoDTO, id);
            return ResponseEntity.ok(veiculo);
        } catch (MarcaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Atualizar um veiculo",
            notes = ".updateVeiculo()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Veiculo atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível atualizar o veiculo"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", required = true, paramType = "path",
                    value = "Id do veiculo", dataType = "Long"),
    })
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    private ResponseEntity updateVeiculoPatch(@PathVariable("id") Long id, @RequestBody JsonPatch patch){
        try {
            Veiculo veiculo = veiculoService.getById(id);
            Veiculo veiculoPatch = applyPatchToCustomer(patch, veiculo);
            veiculoService.update(veiculoPatch);
            return ResponseEntity.ok(veiculoPatch);
        } catch (MarcaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Deletar um veiculo",
            notes = ".deleteVeiculo()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Veiculo deletado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível deletar o veiculo"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", required = true, paramType = "path",
                    value = "Id do veiculo", dataType = "Long"),
    })
    @DeleteMapping("/{id}")
    private ResponseEntity deleteVeiculo(@PathVariable("id") Long id){
        try {
            veiculoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (MarcaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Veiculo applyPatchToCustomer(
            JsonPatch patch, Veiculo targetVeiculo) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetVeiculo, JsonNode.class));
        return objectMapper.treeToValue(patched, Veiculo.class);
    }
}
