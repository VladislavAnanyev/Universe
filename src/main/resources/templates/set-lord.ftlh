<#import "parts/common.ftlh" as c>

<@c.page>

    <div>Повелители: </div>

    <#list lords as lord>
        <div class="mt-2">${lord_index+1}.
            <form action="/planet/${planet.id?c}/set-lord" method="post">
            <div>Идентификатор: ${lord.id?c}</div>
                <input hidden name="id" type="number" value="${lord.id?c}" >
            <div>Имя: ${lord.name}</div>
                <input hidden name="name" type="text" value="${lord.name}" >
            <div>Возраст: ${lord.age}</div>
                <input hidden name="age" type="number" value="${lord.age?c}" >
            <button class="btn btn-primary" type="submit">Назначить повелителем этой планеты</button>
            </form>
        </div>
    </#list>

</@c.page>