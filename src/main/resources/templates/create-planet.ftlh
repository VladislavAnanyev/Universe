<#import "parts/common.ftlh" as c>

<@c.page>

    <form method="post" action="/planet">
        <label>Название планеты
            <input required type="text" name="name">
        </label>
        <button class="btn btn-primary" type="submit">Создать</button>
    </form>

</@c.page>