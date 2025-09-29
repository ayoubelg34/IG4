package lab4;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Downloader {
    public static void main(String[] args) {
        // Lire de bookmarks.txt les liens
        try (BufferedReader br = new BufferedReader(new FileReader("bookmarks.txt"))) {
            String line; //lire la ligne
            while ((line = br.readLine()) != null) {  //tant que pas null appeller downloadFile
                downloadFile(line.trim()); //trim pour enlever les espaces
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile(String urlString) {
        try {
            URI uri = new URI(urlString); //construire URI a partir de urlString
            URL url = uri.toURL(); //construire URL a partir de URI
            String fileName = url.getPath().substring(url.getPath().lastIndexOf('/') + 1);
            String extension = getExtension(fileName);
            if (extension == null) {
                System.out.println("Unknown extension for " + urlString);  //si pas d'extension, on l'ignore
                return;
            }
            Path dir = Paths.get(extension); //créer dossier avec le nom de l'extension
            if (!Files.exists(dir)) {
                Files.createDirectories(dir); //créer le dossier s'il n'existe pas
            }
            Path filePath = dir.resolve(fileName);
            // Télécharger le fichier
            try (InputStream in = url.openStream();
                 FileOutputStream out = new FileOutputStream(filePath.toFile())) { //ouvrir le flux d'entrée et de sortie
                byte[] buffer = new byte[1024]; //buffer arrays
                int bytesRead; //nombre de bytes lus
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead); //écrire dans le fichier
                }
                System.out.println("Downloaded " + fileName + " to " + dir);
            }
        } catch (Exception e) {
            System.out.println("Failed to download " + urlString + ": " + e.getMessage());
        }
    }

    // Extraire l'extension du nom de fichier
    private static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.'); //trouver le dernier point
        if (dotIndex == -1) return null; //si pas de point, pas d'extension
        String ext = fileName.substring(dotIndex + 1).toLowerCase(); //extraire l'extension et la mettre en minuscule
        switch (ext) {
            case "gif": return "gif";
            case "jpg": return "jpg";
            case "jpeg": return "jpg";
            case "pdf": return "pdf";
            case "ps": return "ps";
            case "png": return "png";
            case "txt": return "txt";
            case "zip": return "zip";
            case "html": return "html";
            default: return null;
        }
    }
}
