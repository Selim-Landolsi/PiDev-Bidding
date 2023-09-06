<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230904105059 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE bid (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, produit_id INT NOT NULL, bid_amount DOUBLE PRECISION NOT NULL, entry_date DATE NOT NULL, entry_time TIME NOT NULL, type VARCHAR(255) NOT NULL, INDEX IDX_4AF2B3F3A76ED395 (user_id), INDEX IDX_4AF2B3F3F347EFB (produit_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE exchange (id INT AUTO_INCREMENT NOT NULL, proposition_id INT NOT NULL, response_id INT NOT NULL, entry_date_time DATETIME NOT NULL, UNIQUE INDEX UNIQ_D33BB079DB96F9E (proposition_id), UNIQUE INDEX UNIQ_D33BB079FBF32840 (response_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE payment (id INT AUTO_INCREMENT NOT NULL, payment_id VARCHAR(255) NOT NULL, payer_id VARCHAR(255) NOT NULL, payer_email VARCHAR(255) NOT NULL, amount DOUBLE PRECISION NOT NULL, currency VARCHAR(255) NOT NULL, purchased_at DATETIME NOT NULL, payment_status VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE produit (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, name VARCHAR(255) NOT NULL, brand VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, etat VARCHAR(255) NOT NULL, attribute VARCHAR(255) NOT NULL, value VARCHAR(255) NOT NULL, prix_debut_enchere DOUBLE PRECISION NOT NULL, prix_buy_out DOUBLE PRECISION NOT NULL, livreur_id INT NOT NULL, INDEX IDX_29A5EC27A76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE proposition (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, produit_id INT NOT NULL, INDEX IDX_C7CDC353A76ED395 (user_id), INDEX IDX_C7CDC353F347EFB (produit_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE response (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, produit_id INT NOT NULL, INDEX IDX_3E7B0BFBA76ED395 (user_id), INDEX IDX_3E7B0BFBF347EFB (produit_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, user_name VARCHAR(255) NOT NULL, pass_word VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, role VARCHAR(255) NOT NULL, adresse VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE bid ADD CONSTRAINT FK_4AF2B3F3A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE bid ADD CONSTRAINT FK_4AF2B3F3F347EFB FOREIGN KEY (produit_id) REFERENCES produit (id)');
        $this->addSql('ALTER TABLE exchange ADD CONSTRAINT FK_D33BB079DB96F9E FOREIGN KEY (proposition_id) REFERENCES proposition (id)');
        $this->addSql('ALTER TABLE exchange ADD CONSTRAINT FK_D33BB079FBF32840 FOREIGN KEY (response_id) REFERENCES response (id)');
        $this->addSql('ALTER TABLE produit ADD CONSTRAINT FK_29A5EC27A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE proposition ADD CONSTRAINT FK_C7CDC353A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE proposition ADD CONSTRAINT FK_C7CDC353F347EFB FOREIGN KEY (produit_id) REFERENCES produit (id)');
        $this->addSql('ALTER TABLE response ADD CONSTRAINT FK_3E7B0BFBA76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE response ADD CONSTRAINT FK_3E7B0BFBF347EFB FOREIGN KEY (produit_id) REFERENCES produit (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE bid DROP FOREIGN KEY FK_4AF2B3F3A76ED395');
        $this->addSql('ALTER TABLE bid DROP FOREIGN KEY FK_4AF2B3F3F347EFB');
        $this->addSql('ALTER TABLE exchange DROP FOREIGN KEY FK_D33BB079DB96F9E');
        $this->addSql('ALTER TABLE exchange DROP FOREIGN KEY FK_D33BB079FBF32840');
        $this->addSql('ALTER TABLE produit DROP FOREIGN KEY FK_29A5EC27A76ED395');
        $this->addSql('ALTER TABLE proposition DROP FOREIGN KEY FK_C7CDC353A76ED395');
        $this->addSql('ALTER TABLE proposition DROP FOREIGN KEY FK_C7CDC353F347EFB');
        $this->addSql('ALTER TABLE response DROP FOREIGN KEY FK_3E7B0BFBA76ED395');
        $this->addSql('ALTER TABLE response DROP FOREIGN KEY FK_3E7B0BFBF347EFB');
        $this->addSql('DROP TABLE bid');
        $this->addSql('DROP TABLE exchange');
        $this->addSql('DROP TABLE payment');
        $this->addSql('DROP TABLE produit');
        $this->addSql('DROP TABLE proposition');
        $this->addSql('DROP TABLE response');
        $this->addSql('DROP TABLE user');
        $this->addSql('DROP TABLE messenger_messages');
    }
}
