<%@ include file="init.jsp" %>

<portlet:actionURL var="saveConfigurationURL" />

<form:form modelAttribute="messageForm" action="${saveConfigurationURL}">
    Please enter your name below : <br />

    <form:input path="message" />
    <input type="submit" value="Save" />
</form:form>