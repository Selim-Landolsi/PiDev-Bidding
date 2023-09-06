<?php

namespace App\Entity;

use App\Repository\ExchangeRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ExchangeRepository::class)]
class Exchange
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\OneToOne(cascade: ['persist', 'remove'])]
    #[ORM\JoinColumn(nullable: false)]
    private ?Proposition $proposition = null;

    #[ORM\OneToOne(cascade: ['persist', 'remove'])]
    #[ORM\JoinColumn(nullable: false)]
    private ?Response $response = null;

    #[ORM\Column(type: Types::DATETIME_MUTABLE)]
    private ?\DateTimeInterface $entryDateTime = null;

    public function __construct()
    {
        $this->entryDateTime = new \DateTime("now");
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getProposition(): ?Proposition
    {
        return $this->proposition;
    }

    public function setProposition(Proposition $proposition): self
    {
        $this->proposition = $proposition;

        return $this;
    }

    public function getResponse(): ?Response
    {
        return $this->response;
    }

    public function setResponse(Response $response): self
    {
        $this->response = $response;

        return $this;
    }

    public function getEntryDateTime(): ?\DateTimeInterface
    {
        return $this->entryDateTime;
    }

    public function setEntryDateTime(\DateTimeInterface $entryDateTime): self
    {
        $this->entryDateTime = $entryDateTime;

        return $this;
    }

    
}
