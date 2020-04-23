package com.example.logind.db;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.logind.db.daos.EmpalmeDao;
import com.example.logind.db.entidades.EmpalmeEntity;
import java.util.List;

public class EmpalmeRepositorio {
    private EmpalmeDao empalmeDao;
    private LiveData<List<EmpalmeEntity>> allEmpalmes;
    private List<EmpalmeEntity> allEmpalmesById;
    private LiveData<List<EmpalmeEntity>> allEmpalmesPorTramo;


    public EmpalmeRepositorio(Application application) {
        DataBaseAppCasMag db = DataBaseAppCasMag.getDataBase(application);
        empalmeDao = db.empalmeDao();
        allEmpalmes = empalmeDao.getAllEmpalmes();
        allEmpalmesPorTramo = empalmeDao.getAllEmpalmesByTramo();

    }

    public LiveData<List<EmpalmeEntity>> getAllempalme() {
        return allEmpalmes;
    }


    public LiveData<List<EmpalmeEntity>> getAllPorTramos() {
        return allEmpalmesPorTramo;
    }

//METODO PARA INSERTAR DATOS DEL EMPALME
    public void insertEmpalm(EmpalmeEntity empalmeEntity) {
        new insertEmpalmeAsyntask(empalmeDao).execute(empalmeEntity);
    }

    private static class insertEmpalmeAsyntask extends AsyncTask<EmpalmeEntity, Void, Void> {
        private EmpalmeDao empalmeDaoAsynctask;

        insertEmpalmeAsyntask(EmpalmeDao dao) {
            empalmeDaoAsynctask = dao;
        }

        @Override
        protected Void doInBackground(EmpalmeEntity... empalmeEntities) {
            empalmeDaoAsynctask.insertEmpalme(empalmeEntities[0]);
            return null;
        }
    }
    //METODO PARA ACTUALIZAR DATOS
    public void updateEmpalme(EmpalmeEntity empalmeEntity){
      new updateEmpalmeAsyntask(empalmeDao).execute(empalmeEntity);
    }
    private static class updateEmpalmeAsyntask extends AsyncTask<EmpalmeEntity,Void,Void>{
        private EmpalmeDao empalmeDaoAsynctask;

        updateEmpalmeAsyntask(EmpalmeDao dao){
            empalmeDaoAsynctask=dao;
        }

        @Override
        protected Void doInBackground(EmpalmeEntity... empalmeEntities) {
           empalmeDaoAsynctask.updateEmpalme(empalmeEntities[0]);
            return null;
        }
    }
    //METODO PARA ELIMINAR EMPALMES POR ID
    public void deletEmpalme(EmpalmeEntity empalmeEntity){
        new deletEmpalmeAsyncTask(empalmeDao).execute(empalmeEntity);

    }
    private static  class deletEmpalmeAsyncTask extends AsyncTask<EmpalmeEntity,Void,Void>{
        private EmpalmeDao empalmeDaoDeletAsynctask;
        deletEmpalmeAsyncTask(EmpalmeDao dao){
            empalmeDaoDeletAsynctask = dao;
        }

        @Override
        protected Void doInBackground(EmpalmeEntity... empalmeEntities) {
            empalmeDaoDeletAsynctask.deleteEmpalme(empalmeEntities[0]);
            return null;
        }
    }

}
