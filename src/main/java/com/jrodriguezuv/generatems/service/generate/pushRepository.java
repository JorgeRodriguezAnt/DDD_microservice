package com.jrodriguezuv.generatems.service.generate;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.io.File;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

    public class pushRepository {

        CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();
        public void push(){
            
      









           try {
                // Open the local repository
                 Repository repository = new FileRepository("MS/" + createStructureSpringBoot.nameDir+ "/.git"); 
                
    
                
                  // Create a Git object
            LocalTime horaActual = LocalTime.now();

            // Obtener el día actual
            LocalDate diaActual = LocalDate.now();

            // Formatear la hora
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH-mm-ss");
            String horaFormateada = horaActual.format(formatoHora);

            // Formatear el día
            DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String diaFormateado = diaActual.format(formatoDia);

            Git git = new Git(repository);
            CreateStructureSpringBoot structureSpringBoot = new CreateStructureSpringBoot();
            String  newBranch = structureSpringBoot.nameDir +"_" + diaFormateado + "_" + horaFormateada; 
            
            // Add a new remote
            StoredConfig config = repository.getConfig();
            config.setString("remote", "new-remote", "url", "https://github.com/jorgeRodriguezAntiquera/generated-microservice.git");
            config.save();

            
            
            // Create and checkout a new branch
            git.checkout()
                .setCreateBranch(true)
                .setName(newBranch)
                .call();

            // Stage changes
            git.add().addFilepattern(".").call();

            // Commit changes
            git.commit()
                    .setMessage("Generated microservice")
                    .setAuthor("Jorge Rodriguez", "jorge.rodrigueza@alumnos.uv.cl")
                    .call();

            // Push changes to the new remote and branch
            PushCommand pushCommand = git.push();
            pushCommand.setRemote("new-remote")
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider("jorgeRodriguezAntiquera", "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"))
                    .setRefSpecs(new RefSpec("refs/heads/"+ newBranch +":refs/heads/"+newBranch))
                    .call();

            // Close the repository and Git objects
            git.close();
            repository.close();

            System.out.println("Changes pushed successfully to the new remote and branch!");
        } catch (IOException | GitAPIException e) {
            e.printStackTrace();
        } 
         }


         public void delete(){
            String directoryPath = "MS\\" + createStructureSpringBoot.nameDir;

        // Crear un objeto de tipo File con la ruta del directorio
        File directory = new File(directoryPath);

        // Verificar si el directorio existe
        if (directory.exists()) {
            try {
                // Llamar al método FileUtils.deleteDirectory() para eliminar el directorio y su contenido
                FileUtils.deleteDirectory(directory);
                System.out.println("Directorio eliminado exitosamente.");
            } catch (IOException e) {
                System.out.println("No se pudo eliminar el directorio: " + e.getMessage());
            }
        } else {
            System.out.println("El directorio especificado no existe.");
        }
         }

         /* public void deleteDirectory(File directory){
            File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursivamente eliminar subdirectorios
                    deleteDirectory(file);
                } else {
                    // Eliminar archivos dentro del directorio
                    file.delete();
                }
            }

         }
        } */
 
    }
            
        
    