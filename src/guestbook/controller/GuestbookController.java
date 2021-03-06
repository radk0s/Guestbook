package guestbook.controller;

import guestbook.model.GuestbookModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class GuestbookController extends HttpServlet {

    private GuestbookModel db = GuestbookModel.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    } // doGet

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String name = req.getParameter("nam");
        String mail = req.getParameter("mail");
        String msg = req.getParameter("msg");
        String ip = req.getRemoteAddr();

        if (name != null && mail != null && msg != null && !name.equals("")
                && !mail.equals("") && !msg.equals(""))
            db.update(name, mail, msg, ip);

        session.setAttribute("resultset", db.getResultSet());
        session.setAttribute("checkInfo", " ");

        if (db.getCheckmail())
            session.setAttribute("checkInfo", "Incorrect Email!");

        try {
            if (name.equals("") || mail.equals("") || msg.equals("")) {
                session.setAttribute("checkInfo", "Missing info!");
            }
        } catch (NullPointerException ex) {
            ex.getMessage();
        }

        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, res);
    } // doPost

}
