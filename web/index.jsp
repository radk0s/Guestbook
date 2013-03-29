
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.ResultSet" %>
<html>

<head>
    <title>Guestbook</title>
</head>

<body>
<% ResultSet resultset = (ResultSet) session.getValue("resultset");
    while (resultset.next()) {
%>
<table>
    <tbody>
    <tr>
        <td style="border-style: none;" align="center">
            <table
                    style="border-style: solid; width: 50%; table-layout: fixed; white-space: break-all; word-wrap: break-word;">

                <tr bgcolor="#FBFBFB">
                    <td>
                        <table style="border: none; cellpadding: 0; cellspacing: 0">
                            <tbody>
                            <tr>
                                <td valign="top"><font style="font-size: 8pt;"
                                                       color="#555555" face="Calibri"><b>Date:</b></font></td>
                                <td><font style="font-size: 8pt;" color="#555555"
                                          face="Calibri"><%= resultset.getString("Date") %>
                                </font></td>
                            </tr>
                            <tr>
                                <td valign="top"><font style="font-size: 8pt;"
                                                       color="#555555" face="Calibri"><b>Name:</b></font></td>
                                <td><font style="font-size: 8pt;" color="#555555"
                                          face="Calibri"><%= resultset.getString("Name") %>
                                </font></td>
                            </tr>
                            <tr>
                                <td valign="top"><font style="font-size: 8pt;"
                                                       color="#555555" face="Calibri"><b>IP:</b></font></td>
                                <td><font style="font-size: 8pt;" color="#555555"
                                          face="Calibri"><%= resultset.getString("IPAddr") %>
                                </font></td>
                            </tr>
                            <tr>
                                <td valign="top"><font style="font-size: 8pt;"
                                                       color="#555555" face="Calibri"><b>Email:</b></font></td>
                                <td><font style="font-size: 8pt;" color="#555555"
                                          face="Calibri"><%= resultset.getString("Email") %>
                                </font></td>
                            </tr>
                            </tbody>
                        </table>
                <tr bgcolor="#F4F4F4">
                    <td><font color="#000000" face="Calibri"><%= resultset.getString("Message") %>
                    </font></td>
                </tr>

            </table>
        </td>
    </tr>
    </tbody>
</table>
<% } %>
<form action="guestbook">
    <table>
        <tbody>
        <tr>
            <td style="border-style: none;" align="center">
                <table
                        style="border-style: solid; width: 40%; table-layout: fixed; white-space: break-all; word-wrap: break-word;">

                    <tr bgcolor="#FBFBFB">
                        <td align="center"><font style="font-size: 24pt;"
                                                 color="#555555" face="Calibri"><b>Post new message</b></font></td>
                    </tr>
                    <tr>
                        <td>
                            <table style="border: none; cellpadding: 0; cellspacing: 0">
                                <tbody>
                                <tr>
                                    <td valign="top"><b> <font style="font-size: 12pt;"
                                                               color="#555555" face="Calibri"> Name:</font></b></td>
                                    <td><input type="text" name="nam"></td>
                                </tr>
                                <tr>
                                    <td width=100px valign="top"><b><font
                                            style="font-size: 12pt;" color="#555555" face="Calibri">
                                        Email:</font></b></td>
                                    <td><input type="text" name="mail"></td>
                                </tr>
                                <tr>
                                    <td valign="top"><b><font style="font-size: 12pt;"
                                                              color="#555555" face="Calibri"> Message:</font></b></td>
                                    <td><textarea
                                            style="width: 400px; height: 200ph; resize: none"
                                            name="msg" rows="7" cols="29"></textarea></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="center"><b><font style="font-size: 12pt;"
                                                    color="#555555" face="Calibri"> <input type="submit"
                                                                                           name="submit"
                                                                                           value="Send now!"></font></b>
                        </td>
                    </tr>
                    <tr>
                        <td align="center"><b><font style="font-size: 12pt;" color="#555555" face="Calibri">
                            <%=session.getValue("checkInfo") %>
                        </font></b></td>
                    </tr>

                </table>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>