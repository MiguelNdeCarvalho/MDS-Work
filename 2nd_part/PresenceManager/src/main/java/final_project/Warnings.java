package final_project;
import java.util.*;

public class Warnings {

    private List<User> warned_50;
    private List<User> warned_25;

    public Warnings()
    {
        warned_50 = new ArrayList<User>();
        warned_25 = new ArrayList<User>();
    }

    public List<User> get_L()
    {
        return warned_50;
    }

    public List<User> get_XXV()
    {
        return warned_25;
    }

    public static double assiduidade_of(User aluno,double n_valid_lessons)
    {
        if (n_valid_lessons==0) {
            
            return 100;
        }

        double n_valid_presences=0;

        for(int i = 0; i<aluno.get_Presence().size(); i++)
        {
            if (aluno.get_Presence().get(i).getValid()==true) {
                n_valid_presences=n_valid_presences+aluno.get_Presence().get(i).getPresence_value();
            }
        }

        return (n_valid_presences/n_valid_lessons)*100;
    }

    public void update(List<User> alunos,double n_valid_lessons)
    {
        User actual_aluno;
        double assiduidade_atual;

        //remover os alunos que não se encontram em risco
        for (int i = 0; i < this.warned_25.size(); i++) {
            
            actual_aluno=this.warned_25.get(i);
            assiduidade_atual=assiduidade_of(actual_aluno, n_valid_lessons);
            
            if ( assiduidade_atual>75) {
                this.warned_25.remove(i);
            }
        }

        for (int i = 0; i < this.warned_50.size(); i++) {
            
            actual_aluno=this.warned_50.get(i);
            assiduidade_atual=assiduidade_of(actual_aluno, n_valid_lessons);

            if ( assiduidade_atual>50) {
                this.warned_50.remove(i);
            }

        }
        
        //adicionar os alunos que não se encontram em risco
        for (int i = 0; i < alunos.size(); i++) {
            
            actual_aluno=alunos.get(i);
            assiduidade_atual=assiduidade_of(actual_aluno, n_valid_lessons);
            
            if (assiduidade_atual>=50 && assiduidade_atual<75) {
                
                boolean existe = false;
                for (int j = 0; j < this.warned_25.size(); j++) {
                    
                    if (actual_aluno.equals(this.warned_25.get(j))) {
                        existe=true;
                    }
                }

                if (!existe) {
                    
                    this.warned_25.add(actual_aluno);
                    //send_email_25(actual aluno)
                }
            }

            if (assiduidade_atual<50) {
                
                boolean existe = false;
                for (int j = 0; j < this.warned_25.size(); j++) {
                    
                    if (actual_aluno.equals(this.warned_25.get(j))) {
                        existe=true;
                    }
                }

                if (!existe) {
                    
                    this.warned_50.add(actual_aluno);
                    //send_email_50(actual aluno)
                }
            }
        }
    
    }
//>  <

}