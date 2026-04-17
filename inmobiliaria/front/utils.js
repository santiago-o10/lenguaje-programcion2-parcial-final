// Verifica sesión, redirige al login si no hay
function checkSesion() {
    const u = sessionStorage.getItem("usuario");
    if (!u) {
        // Detectar si estamos en la raíz o en una subcarpeta
        const depth = window.location.pathname.split('/').filter(Boolean).length;
        const loginPath = depth <= 1 ? 'login/login.html' : '../login/login.html';
        window.location.href = loginPath;
        return null;
    }
    return JSON.parse(u);
}
 
// Sidebar para páginas en subcarpetas (usa ../ para subir un nivel)
function getSidebar(active) {
    return _buildSidebar(active, '../');
}
 
// Sidebar para index.html que está en la raíz (sin ../)
function getSidebarRoot(active) {
    return _buildSidebar(active, '');
}
 
function _buildSidebar(active, prefix) {
    return `
    <div class="sidebar-brand">
        <div class="brand-icon"><i class="bi bi-building-fill"></i></div>
        <div class="text-white fw-bold mt-1" style="font-size:0.95rem;">Inmobiliaria</div>
        <div class="text-white-50" style="font-size:0.75rem;">Sistema de Gestión</div>
    </div>
    <nav class="mt-2">
        <div class="sidebar-section">Principal</div>
        <ul class="nav flex-column">
            <li><a href="${prefix}index.html" class="nav-link ${active==='dashboard'?'active':''}"><i class="bi bi-speedometer2"></i> Dashboard</a></li>
        </ul>
        <div class="sidebar-section">Personas</div>
        <ul class="nav flex-column">
            <li><a href="${prefix}tipoPersona/tipoPersona.html" class="nav-link ${active==='tipoPersona'?'active':''}"><i class="bi bi-tags"></i> Tipo Persona</a></li>
            <li><a href="${prefix}persona/persona.html" class="nav-link ${active==='persona'?'active':''}"><i class="bi bi-people"></i> Personas</a></li>
            <li><a href="${prefix}empleado/empleado.html" class="nav-link ${active==='empleado'?'active':''}"><i class="bi bi-person-badge"></i> Empleados</a></li>
            <li><a href="${prefix}cliente/cliente.html" class="nav-link ${active==='cliente'?'active':''}"><i class="bi bi-person-check"></i> Clientes</a></li>
            <li><a href="${prefix}propietario/propietario.html" class="nav-link ${active==='propietario'?'active':''}"><i class="bi bi-person-workspace"></i> Propietarios</a></li>
        </ul>
        <div class="sidebar-section">Ubicación</div>
        <ul class="nav flex-column">
            <li><a href="${prefix}pais/pais.html" class="nav-link ${active==='pais'?'active':''}"><i class="bi bi-globe-americas"></i> Países</a></li>
            <li><a href="${prefix}departamento/departamento.html" class="nav-link ${active==='departamento'?'active':''}"><i class="bi bi-map"></i> Departamentos</a></li>
            <li><a href="${prefix}ciudad/ciudad.html" class="nav-link ${active==='ciudad'?'active':''}"><i class="bi bi-geo-alt"></i> Ciudades</a></li>
        </ul>
        <div class="sidebar-section">Gestión</div>
        <ul class="nav flex-column">
            <li><a href="${prefix}apartamento/apartamento.html" class="nav-link ${active==='apartamento'?'active':''}"><i class="bi bi-house-door"></i> Apartamentos</a></li>
            <li><a href="${prefix}contrato/contrato.html" class="nav-link ${active==='contrato'?'active':''}"><i class="bi bi-file-earmark-text"></i> Contratos</a></li>
            <li><a href="${prefix}formaPago/formaPago.html" class="nav-link ${active==='formaPago'?'active':''}"><i class="bi bi-credit-card"></i> Forma de Pago</a></li>
            <li><a href="${prefix}pagoContrato/pagoContrato.html" class="nav-link ${active==='pagoContrato'?'active':''}"><i class="bi bi-cash-coin"></i> Pagos Contrato</a></li>
            <li><a href="${prefix}mantenimiento/mantenimiento.html" class="nav-link ${active==='mantenimiento'?'active':''}"><i class="bi bi-tools"></i> Mantenimiento</a></li>
            <li><a href="${prefix}pagoMantenimiento/pagoMantenimiento.html" class="nav-link ${active==='pagoMantenimiento'?'active':''}"><i class="bi bi-receipt"></i> Pagos Mantenimiento</a></li>
        </ul>
        <div class="sidebar-section">Seguridad</div>
        <ul class="nav flex-column">
            <li><a href="${prefix}perfil/perfil.html" class="nav-link ${active==='perfil'?'active':''}"><i class="bi bi-shield-check"></i> Perfiles</a></li>
            <li><a href="${prefix}usuario/usuario.html" class="nav-link ${active==='usuario'?'active':''}"><i class="bi bi-person-lock"></i> Usuarios</a></li>
        </ul>
    </nav>`;
}
 
function cerrarSesion() {
    if (confirm("¿Desea cerrar sesión?")) {
        sessionStorage.removeItem("usuario");
        // Obtener la ruta base del servidor
        const pathname = window.location.pathname;
        const basePath = pathname.substring(0, pathname.lastIndexOf('/'));
        // Redirigir a usuario.html en la misma carpeta base
        window.location.href = basePath + '/usuario/usuario.html';
    }
}
 
function mostrarAlerta(id, msg, tipo = "danger") {
    const el = document.getElementById(id);
    if (!el) return;
    el.className = `alert alert-${tipo}`;
    el.innerHTML = msg;
    el.style.display = "block";
    setTimeout(() => el.style.display = "none", 4000);
}
 
async function cargarSelect(selectId, url, valField, labelFn) {
    const sel = document.getElementById(selectId);
    if (!sel) return;
    try {
        const res = await fetch(url);
        const data = await res.json();
        sel.innerHTML = '<option value="">-- Seleccione --</option>';
        data.forEach(item => {
            const opt = document.createElement("option");
            opt.value = item[valField];
            opt.textContent = labelFn(item);
            sel.appendChild(opt);
        });
    } catch { sel.innerHTML = '<option value="">Error al cargar</option>'; }
}