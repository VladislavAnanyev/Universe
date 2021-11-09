<#import "parts/common.ftlh" as c>

<@c.page>

    <form method="post" action="/lord">
        <label>Имя повелителя
            <input required type="text" name="name">
        </label>
        <label>Возраст
            <input required type="number" name="age">
        </label>
        <button class="btn btn-primary" type="submit">Создать</button>
    </form>

</@c.page>