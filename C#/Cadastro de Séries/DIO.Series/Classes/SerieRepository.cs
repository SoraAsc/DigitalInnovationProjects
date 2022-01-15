using System.Collections.Generic;
using DIO.Series.Interfaces;

namespace DIO.Series.Classes
{
    public class SerieRepository : IRepository<Serie>
    {
        private List<Serie> series = new List<Serie>();
        private int proximaId = 0;
        public void Atualizar(int id, Serie entidade)
        {
            int serieIndex = series.FindIndex(item => item.retornaId() == id);
            series[serieIndex] = entidade;
            //series[id] = entidade;
        }

        public void Excluir(int id)
        {
            int indexExcluido = series.FindIndex(item => item.retornaId() == id);
            series.RemoveAt(indexExcluido);
            //series[id].Excluir();

        }

        public void Inserir(Serie entidade)
        {
            series.Add(entidade);
            if (entidade.retornaId() >= proximaId) proximaId = entidade.retornaId() + 1;
        }

        public List<Serie> Listar()
        {
            return series;
        }

        public int ProximoId()
        {
            return proximaId;
            //return series.Count;
        }

        public Serie RetornaPorId(int id)
        {
            return series.Find(item => item.retornaId() == id);
            //return series[id];
        }
    }
}