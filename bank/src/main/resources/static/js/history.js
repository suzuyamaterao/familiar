// 初期表示時に実行
document.addEventListener('DOMContentLoaded', () => {
    updateMonthDisplay();  // 月の表示と取引履歴の更新
});

const transactions = [
    { id: 1, date: '2025-02-15', amount: 75000, counterparty: '佐藤太郎', note: '家賃収入' },
    { id: 2, date: '2025-02-12', amount: -5000, counterparty: '山田花子', note: 'ギフト購入' },
    { id: 3, date: '2025-02-10', amount: -2000, counterparty: '鈴木一郎', note: 'ランチ代' }
];

let currentMonth = new Date().getMonth() + 1;
let currentYear = new Date().getFullYear();

function updateMonthDisplay() {
    document.getElementById('current-month').innerText = `${currentYear}年${currentMonth}月`;
    showTransactionsForMonth(currentYear, currentMonth);
}

function changeMonth(delta) {
    currentMonth += delta;
    if (currentMonth === 0) {
        currentMonth = 12;
        currentYear--;
    } else if (currentMonth === 13) {
        currentMonth = 1;
        currentYear++;
    }
    updateMonthDisplay();
}

function showTransactionsForMonth(year, month) {
    const transactionList = document.getElementById('transaction-list');
    const noTransactions = document.getElementById('no-transactions');
    transactionList.innerHTML = '';
    let hasTransactions = false;

    transactions.forEach(transaction => {
        const transactionDate = new Date(transaction.date);
        if (transactionDate.getFullYear() === year && transactionDate.getMonth() + 1 === month) {
            hasTransactions = true;
            const li = document.createElement('li');
            li.classList.add('transaction-item');
            const amountClass = transaction.amount < 0 ? 'negative' : '';
            li.innerHTML = `
                <div>
                    <div><strong>${transaction.counterparty}</strong></div>
                    <div class="transaction-date">${transaction.date}</div>
                </div>
                <div class="transaction-amount ${amountClass}">${transaction.amount.toLocaleString()}円</div>
            `;
            transactionList.appendChild(li);
        }
    });

    if (hasTransactions) {
        noTransactions.style.display = 'none';
    } else {
        noTransactions.style.display = 'block';
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const userIcon = document.getElementById("userIcon");
    const userDropdown = document.getElementById("userDropdown");
    const closeDropdown = document.getElementById("closeDropdown");

    userIcon.addEventListener("click", function() {
        userDropdown.classList.toggle("show");
    });

    closeDropdown.addEventListener("click", function() {
        userDropdown.classList.remove("show");
    });
});


