<#import "parts/common.ftlh" as c>

<@c.page>

    <div>Планеты: </div>

    <#list planets as planet>
        <div class="mt-2">${planet_index+1}.
            <div>Идентификатор: ${planet.id?c}</div>
            <div>Название: ${planet.name}</div>
            <div>Повелитель:
                <#if planet.lord??>
                    ${planet.lord.name}
                <#else>
                    Не назначен
                    <form method="get" action="/planet/${planet.id?c}/set-lord">
                    <button class="btn btn-primary">Назначить повелителя</button>
                    </form>
                </#if>
            </div>
            <form method="post" action="/planet/${planet.id?c}/delete">
            <button class="btn btn-primary mt-2">Удалить планету</button>
            </form>
        </div>
    </#list>

</@c.page>