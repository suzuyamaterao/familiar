document.addEventListener('DOMContentLoaded', async () => {

    // =========================
    // 要素
    // =========================
    const el = {
        unit: document.getElementById('unitSelect'),
        team: document.getElementById('teamSelect'),
        emp: document.getElementById('employeeSelect'),

        unitHidden: document.getElementById('unitHidden'),
        teamHidden: document.getElementById('teamHidden'),
        nameHidden: document.getElementById('nameHidden')
    };

    // =========================
    // 状態（Single Source of Truth）
    // =========================
    const state = {
        role: rolesId,
        unit: currentUnitNo || '',
        team: currentTeamId || '',
        emp: currentEmpId || ''
    };

    // 初期データ保持
    let master = {
        teams: [],
        employees: []
    };

    // =========================
    // 初期化
    // =========================
    await initMaster();
    bindEvents();
    await initViewByRole();
    syncHidden();

    // =========================
    // イベント
    // =========================
    function bindEvents() {
        el.unit.addEventListener('change', async () => {
            state.unit = el.unit.value;
            state.team = '';
            state.emp = '';

            await loadTeams();
            await loadEmployees();

            render();
        });

        el.team.addEventListener('change', async () => {
            state.team = el.team.value;
            state.emp = '';

            await loadEmployees();

            render();
        });

        el.emp.addEventListener('change', () => {
            state.emp = el.emp.value;
            syncHidden();
        });
    }

    // =========================
    // 初期データ
    // =========================
    async function initMaster() {
        master.teams = Array.from(el.team.options).slice(1).map(o => ({
            teamId: o.value,
            teamName: o.text
        }));

        master.employees = Array.from(el.emp.options).slice(1).map(o => ({
            empId: o.value,
            empName: o.text
        }));
    }

    // =========================
    // ロール初期化
    // =========================
    async function initViewByRole() {

        if (state.role === '1') {
            return;
        }

        if (state.role === '2') {
            el.unit.disabled = true;
            await loadTeams();
            await loadEmployees();
        }

        if (state.role === '3') {
            el.unit.disabled = true;
            el.team.disabled = true;

            await loadTeams();
            await loadEmployees();
        }

        if (state.role === '4') {
            el.unit.disabled = true;
            el.team.disabled = true;
            el.emp.disabled = true;

            await loadTeams();
            await loadEmployees();
        }

        render();
    }

    // =========================
    // データ取得
    // =========================
    async function loadTeams() {
        if (!state.unit) {
            renderTeams(master.teams);
            return;
        }

        const res = await fetch(`/api/teams-by-unit?unitNo=${state.unit}`);
        const data = await res.json();

        renderTeams(data.teams);
    }

    async function loadEmployees() {

        if (state.team) {
            const res = await fetch(`/api/employees-by-team?teamId=${state.team}`);
            const data = await res.json();
            renderEmployees(data.employees);
            return;
        }

        if (state.unit) {
            const res = await fetch(`/api/employees-by-unit?unitNo=${state.unit}`);
            const data = await res.json();
            renderEmployees(data.employees);
            return;
        }

        renderEmployees(master.employees);
    }

    // =========================
    // 描画
    // =========================
    function render() {
        el.unit.value = state.unit;
        el.team.value = state.team;
        el.emp.value = state.emp;

        syncHidden();
    }

    function renderTeams(teams) {
        el.team.innerHTML = '<option value="">選択してください</option>';

        teams.forEach(t => {
            const opt = document.createElement('option');
            opt.value = t.teamId;
            opt.text = t.teamName;
            el.team.appendChild(opt);
        });

        el.team.value = state.team;
    }

    function renderEmployees(employees) {
        el.emp.innerHTML = '<option value="">選択してください</option>';

        employees.forEach(e => {
            const opt = document.createElement('option');
            opt.value = e.empId;
            opt.text = e.empLname
                ? `${e.empLname} ${e.empFname}`
                : e.empName;
            el.emp.appendChild(opt);
        });

        el.emp.value = state.emp;
    }

    // =========================
    // hidden同期
    // =========================
    function syncHidden() {
        el.unitHidden.value = state.unit;
        el.teamHidden.value = state.team;
        el.nameHidden.value = state.emp;
    }
    
    document.getElementById('csvBtn').addEventListener('click', () => {

        const params = new URLSearchParams();

        params.append('startDate', document.querySelector('[name="startDate"]').value);
        params.append('endDate', document.querySelector('[name="endDate"]').value);
        params.append('units', document.getElementById('unitHidden').value);
        params.append('teams', document.getElementById('teamHidden').value);
        params.append('name', document.getElementById('nameHidden').value);

        window.location.href = '/export-csv?' + params.toString();
    });

});