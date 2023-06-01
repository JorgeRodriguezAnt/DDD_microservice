package com.jrodriguezuv.generatems.service.generate;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.transport.*;


import java.io.IOException;
import java.net.URISyntaxException;



    public class pushRepository {

        public void push(){
            CreateStructureSpringBoot createStructureSpringBoot = new CreateStructureSpringBoot();

            try {
                // Open the local repository
                Repository repository = new FileRepository("MS\\" + createStructureSpringBoot.nameDir+ "\\.git");
    
                
                  // Create a Git object
            Git git = new Git(repository);
            CreateStructureSpringBoot structureSpringBoot = new CreateStructureSpringBoot();
            String  newBranch = structureSpringBoot.nameDir; 
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
                    .setMessage("My commit message")
                    .setAuthor("John Doe", "john.doe@example.com")
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
        }
 
        
            
        
    