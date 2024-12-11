package com.doublevpartners.ticketmanagement.controller;

import com.doublevpartners.ticketmanagement.dto.request.GetTicketBetween;
import com.doublevpartners.ticketmanagement.dto.request.TicketInDto;
import com.doublevpartners.ticketmanagement.dto.request.TicketManagementInDto;
import com.doublevpartners.ticketmanagement.dto.response.ResponseDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketManagementOutDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketOutDto;
import com.doublevpartners.ticketmanagement.service.TicketManagementService;
import com.doublevpartners.ticketmanagement.service.TicketService;
import com.doublevpartners.ticketmanagement.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/tickets")
@Tag(name = "Gestion de tickets", description = "Creacion, gestion, cierre, actualizacion y comentarios sobre tickets")
public class TicketController {
    private final TicketService ticketService;
    private final TicketManagementService ticketManagementService;

    @Autowired
    public TicketController(TicketService ticketService,
                            TicketManagementService ticketManagementService) {
        this.ticketService = ticketService;
        this.ticketManagementService = ticketManagementService;
    }

    @GetMapping("/{ticketId}")
    @Operation(
            summary = "Buscar ticket por id"
    )
    public ResponseEntity<ResponseDto<TicketOutDto>> getByTicketId(@PathVariable Long ticketId){
        return new ResponseEntity<>(
                ResponseDto.<TicketOutDto>builder()
                        .data(ticketService.getById(ticketId))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/new")
    @Operation(
            summary = "Crear nuevo ticket (ADMIN Y USER)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<TicketOutDto>> createTicket(@RequestBody TicketInDto ticket){
        return new ResponseEntity<>(
                ResponseDto.<TicketOutDto>builder()
                        .data(ticketService.createTicket(ticket))
                        .message(Constants.CREATED_TICKET)
                        .status(HttpStatus.CREATED)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PutMapping("/update/{ticketId}")
    @Operation(
            summary = "Actualizar ticket (ADMIN Y USER)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<TicketOutDto>> updateTicket(@PathVariable Long ticketId, @RequestBody TicketInDto ticket){
        return new ResponseEntity<>(
                ResponseDto.<TicketOutDto>builder()
                        .data(ticketService.updateTicket(ticketId, ticket))
                        .message(Constants.UPDATED_TICKET)
                        .status(HttpStatus.CREATED)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/{ticketId}")
    @Operation(
            summary = "Eliminar ticket y sus gestiones (ADMIN)"
    )
    public ResponseEntity<ResponseDto<Void>> deleteTicket(@PathVariable Long ticketId){
        ticketService.deleteTicket(ticketId);
        return new ResponseEntity<>(
                ResponseDto.<Void>builder()
                        .data(null)
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/management")
    @Operation(
            summary = "Crear novedad en ticket",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<TicketManagementOutDto>> newManagementTicket(@RequestBody TicketManagementInDto ticketManagement){
        return new ResponseEntity<>(
                ResponseDto.<TicketManagementOutDto>builder()
                        .data(ticketManagementService.create(ticketManagement))
                        .message(Constants.CREATED_COMMENT)
                        .status(HttpStatus.CREATED)
                        .build(),
                HttpStatus.CREATED
        );
    }
    @GetMapping("/all/page={page}/size={size}")
    @Operation(
            summary = "Muestra todos los tickets",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<Page<TicketOutDto>>> getAll(
            @PathVariable int page,
            @PathVariable int size
    ) {
        return new ResponseEntity<>(
                ResponseDto.<Page<TicketOutDto>>builder()
                        .data(ticketService.getAll(PageRequest.of(page, size)))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all/s={search}/page={page}/size={size}")
    @Operation(
            summary = "Muestra todos los tickets que su nombre o descripción coincidan con la palabra ingresada",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<Page<TicketOutDto>>> getByNameOrDescriptionContaining(
            @PathVariable String search,
            @PathVariable int page,
            @PathVariable int size
    ) {
        return new ResponseEntity<>(
                ResponseDto.<Page<TicketOutDto>>builder()
                        .data(ticketService.getByNameOrDescriptionContaining(PageRequest.of(page, size), search))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all/status={ticketStatus}/page={page}/size={size}")
    @Operation(
            summary = "Muestra todos los tickets según el estado indicado (ACTIVE, ATTENDING, RESOLVED, CLOSED)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<Page<TicketOutDto>>> getByTicketStatus(
            @PathVariable String ticketStatus,
            @PathVariable int page,
            @PathVariable int size
    ) {
        return new ResponseEntity<>(
                ResponseDto.<Page<TicketOutDto>>builder()
                        .data(ticketService.getByTicketStatus(PageRequest.of(page, size), ticketStatus))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all/type={ticketType}/page={page}/size={size}")
    @Operation(
            summary = "Muestra todos los tickets según el tipo indicado (BUG, REQUEST)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<Page<TicketOutDto>>> getByTicketType(
            @PathVariable String ticketType,
            @PathVariable int page,
            @PathVariable int size
    ) {
        return new ResponseEntity<>(
                ResponseDto.<Page<TicketOutDto>>builder()
                        .data(ticketService.getByTicketType(PageRequest.of(page, size), ticketType))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all/username={username}/page={page}/size={size}")
    @Operation(
            summary = "Muestra todos los tickets reportados por un usuario",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<Page<TicketOutDto>>> getByTicketUsername(
            @PathVariable String username,
            @PathVariable int page,
            @PathVariable int size
    ) {
        return new ResponseEntity<>(
                ResponseDto.<Page<TicketOutDto>>builder()
                        .data(ticketService.getByUsername(PageRequest.of(page, size), username))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/all/page={page}/size={size}")
    @Operation(
            summary = "Muestra todos los tickets creados dentro del rango de fechas establecido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ResponseDto<Page<TicketOutDto>>> getByCreatedAt(
            @PathVariable int page,
            @PathVariable int size,
            @RequestBody GetTicketBetween getTicketBetween
    ) {
        return new ResponseEntity<>(
                ResponseDto.<Page<TicketOutDto>>builder()
                        .data(ticketService.getByCreatedAtBetween(PageRequest.of(page, size), getTicketBetween.getStartDate(), getTicketBetween.getEndDate()))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }


}
