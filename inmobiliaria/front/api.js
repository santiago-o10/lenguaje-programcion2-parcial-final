const API_BASE = "http://localhost:5052/api";

const API = {
    health:              `${API_BASE}/health`,
    login:               `${API_BASE}/usuarios/login`,
    registrar:           `${API_BASE}/usuarios/registrar`,
    usuarios:            `${API_BASE}/usuarios`,
    perfiles:            `${API_BASE}/perfiles`,
    tiposPersona:        `${API_BASE}/tipo-persona`,
    personas:            `${API_BASE}/personas`,
    empleados:           `${API_BASE}/empleados`,
    clientes:            `${API_BASE}/clientes`,
    propietarios:        `${API_BASE}/propietarios`,
    paises:              `${API_BASE}/paises`,
    departamentos:       `${API_BASE}/departamentos`,
    ciudades:            `${API_BASE}/ciudades`,
    apartamentos:        `${API_BASE}/apartamentos`,
    contratos:           `${API_BASE}/contratos`,
    formasPago:          `${API_BASE}/formas-pago`,
    pagosContrato:       `${API_BASE}/pagos-contrato`,
    mantenimientos:      `${API_BASE}/mantenimientos`,
    pagosMantenimiento:  `${API_BASE}/pagos-mantenimiento`,
};
