<#import "parts/common.ftlh" as c>

<@c.page>

    <div>Повелители: </div>

    <#list lords as lord>
        <div class="mt-2">${lord_index+1}.
            <div>Идентификатор: ${lord.id?c}</div>
            <div>Имя: ${lord.name}</div>
            <div>Возраст: ${lord.age}</div>
        </div>
    </#list>

</@c.page>