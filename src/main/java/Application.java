import entity.Player;
import entity.Team;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.*;

public class Application {

    public static void main(String[] args) {
//        Team team1 = new Team("Navi");
//        Team team2 = new Team("Virtus");
//        Team team3 = new Team("OG");
//        Team team4 = new Team("Top Secret");
//
//        createOrUpdate(team1);
//        createOrUpdate(team2);
//        createOrUpdate(team3);
//        createOrUpdate(team4);
//
//        List<Team> teamList1 = new LinkedList<>();
//        teamList1.add(team1);
//        teamList1.add(team2);
//
//        List<Team> teamList2 = new LinkedList<>();
//        teamList2.add(team2);
//
//        List<Team> teamList3 = new LinkedList<>();
//        teamList3.add(team1);
//        teamList3.add(team2);
//        teamList3.add(team3);
//        teamList3.add(team4);
//
//        Player player1 = new Player("C_Ivan", "1234", LocalDate.now().minusDays(10), teamList1);
//        Player player2 = new Player("C_Oleg", "4321", LocalDate.now().minusDays(9), teamList1);
//        Player player3 = new Player("A_Igor", "43210", LocalDate.now().minusDays(11), teamList3);
//        Player player4 = new Player("D_Konstantin", "zxc", LocalDate.now().minusDays(2), teamList1);
//        Player player5 = new Player("B_Pavel", "zxc", LocalDate.now().minusDays(20), teamList2);
//
//        createOrUpdate(player1);
//        createOrUpdate(player2);
//        createOrUpdate(player3);
//        createOrUpdate(player4);
//        createOrUpdate(player5);

        List<Player> players = getAllPlayersSortedByDate(LocalDate.of(2021, 4, 11));
        players.forEach(x -> System.out.println(x));

        System.out.println();
        System.out.println();

        List<Player> playersByAlphabet = getAllByAlphabet();
        playersByAlphabet.forEach(x -> System.out.println(x));

    }

    public static <T> void createOrUpdate(T entity){
        Session hs = HibernateUtil.getSessionFactory().openSession();
        hs.beginTransaction();
        hs.saveOrUpdate(entity);
        hs.getTransaction().commit();
        hs.close();
    }

    public static List<Player> getAllByAlphabet() {
        Session hs = HibernateUtil.getSessionFactory().openSession();
        hs.beginTransaction();
        Query query = hs.createQuery("select p from Player p order by p.login ASC");
        List<Player> players = query.list();
        return players;
    }

    public static List<Player> getAllPlayersSortedByDate(LocalDate localDate) {
        Session hs = HibernateUtil.getSessionFactory().openSession();
        hs.beginTransaction();
        return hs.createQuery("select p from Player p " +
                "where p.date > '2021-04-11'").list();
    }
}
