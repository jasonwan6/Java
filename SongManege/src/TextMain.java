import java.net.ConnectException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.Dao.www.SongDao;
import com.Dao.www.SongDaoImpl;
import com.Dao.www.UserDao;
import com.Dao.www.UserDaoImpl;
import com.Unity.www.Song;
import com.Unity.www.User;
import com.View.www.AdminSongManageView;
import com.View.www.LoginView;
import com.View.www.UserSongManageView;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class TextMain {
	
	public static void main(String[] args) {
		new LoginView();
	}
}
