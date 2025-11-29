fetch("/books")
    .then(r => r.json())
    .then(data => {
        let div = document.getElementById("books");
        data.forEach(b => {
            div.innerHTML += `
                <div class="book">
                    <b>${b.title}</b> — ${b.author} (${b.year})  
                    <br>Жанр: ${b.genre}, статус: ${b.status}
                </div>`;
        });
    });
